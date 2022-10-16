package oop.io.demo.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.ConfirmationToken.ConfirmationTokenRepository;
import oop.io.demo.auth.ConfirmationToken.ConfirmationTokenService;
import oop.io.demo.auth.payload.request.LoginRequest;
import oop.io.demo.auth.payload.request.SignupRequest;
import oop.io.demo.auth.payload.response.JwtResponse;
import oop.io.demo.auth.payload.response.MessageResponse;
import oop.io.demo.auth.security.jwt.JwtUtils;
import oop.io.demo.auth.security.services.UserDetailImplementation;
import oop.io.demo.mail.EmailService;
import oop.io.demo.user.UserRepository;
import oop.io.demo.user.UserService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository repository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public AuthController(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository) {
        this.repository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        /* if(!(loginRequest.getEmail().matches("[a-z0-9]+@sportsschool.edu.sg")) && !(loginRequest.getEmail().matches("[a-z0-9]+@nysi.org.sg"))){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is not a slay email!"));
        } */
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImplementation userDetails = (UserDetailImplementation) authentication.getPrincipal();
        if(!userDetails.isEnabled()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: You are not authenticated yet. Please check your inbox for an email with a link to complete your registration."));
            //we might want to have a button 'Resend email' which triggers a service to send an email with the link to complete registration
        }

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                                    userDetails.getEmail(), userDetails.getName(),
                                    userDetails.getAuthority()));
        }

        //this is the first step to signing up (just using name and email)
        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
            if (repository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            } /* else if(!(signUpRequest.getEmail().matches("[a-z0-9]+@sportsschool.edu.sg")) && !(signUpRequest.getEmail().matches("[a-z0-9]+@nysi.org.sg"))){
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is not a slay email!"));
            } */
            AuthService authService = new AuthService(repository, confirmationTokenRepository);
            return authService.signUpOneUser(signUpRequest);
        }

        @GetMapping("/confirm")
        public String confirmUser(@RequestParam("token") String token) {
            AuthService authService = new AuthService(repository, confirmationTokenRepository);
            return authService.confirmToken(token);
        }

        @PostMapping("/signout")
        public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok()
            .body(new MessageResponse("You've been signed out!"));

        }
}
