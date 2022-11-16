/**
 * Contains endpoints to be used for authentication
 * 1. Sign in   [POST] /auth/signin
 * 2. Sign up   [POST] /auth/signup
 * 3. Verify account from token received after signing up [GET] /auth/confirm
 * 4. Sign out    
 */

package oop.io.demo.auth;

import java.util.Map;

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

import oop.io.demo.auth.confirmationToken.ConfirmationToken;
import oop.io.demo.auth.confirmationToken.ConfirmationTokenRepository;
import oop.io.demo.auth.confirmationToken.ConfirmationTokenService;
import oop.io.demo.auth.payload.request.LoginRequest;
import oop.io.demo.auth.payload.request.SignupRequest;
import oop.io.demo.auth.payload.request.VerificationRequest;
import oop.io.demo.auth.payload.response.JwtResponse;
import oop.io.demo.auth.payload.response.MessageResponse;
import oop.io.demo.auth.security.jwt.JwtUtils;
import oop.io.demo.auth.security.services.UserDetailImplementation;
import oop.io.demo.exception.EmailFailToSendException;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

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

    private final UserRepository userRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private ConfirmationTokenService confirmationTokenService;

    public AuthController(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
                                    userDetails.getName(),
                                    userDetails.getAuthority()));
        }

        //this is the first step to signing up (just using name and email)
        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody Map<String, String> signUpRequest){
            AuthService authService = new AuthService(userRepository, confirmationTokenRepository);
            try {
                if (userRepository.existsByEmail(signUpRequest.get("email"))) {
                    User u = userRepository.findByEmail(signUpRequest.get("email")).get();
                    if(!u.isVerified()){
                        authService.sendConfirmationTokenEmail(u);
                        return ResponseEntity.badRequest().body(new MessageResponse("Error: User with this email is already registered. Please check email for verification link."));
                    }
                    return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use! Please log in instead."));
                } /* else if(!(signUpRequest.getEmail().matches("[a-z0-9]+@sportsschool.edu.sg")) && !(signUpRequest.getEmail().matches("[a-z0-9]+@nysi.org.sg"))){
                    return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is not a slay email!"));
                } */
                return authService.signUpOneUser(new SignupRequest(signUpRequest.get("email"), signUpRequest.get("name")));
            } catch(EmailFailToSendException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            catch(Exception e) {
                return ResponseEntity.badRequest().body("Unable to sign up user.");
            }
    
        }

        @GetMapping("/confirm")
        public ResponseEntity<?> confirmUser(@RequestParam("token") String token, @RequestParam("password") String password, @RequestParam("contactno") String contactno) {
            AuthService authService = new AuthService(userRepository, confirmationTokenRepository);
            ConfirmationToken confirmationToken = authService.confirmToken(token);
            //set password           
            User user = confirmationToken.getUser();
            user.setPassword(encoder.encode(password));
            user.setContactNo(contactno);
            
            //set confirmedAt to now
            confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
            confirmationTokenService.setConfirmedAt(token);

            //set isVerified to equal true
            user.setVerified(true);
            userRepository.save(user);

            return ResponseEntity.ok("Confirmed");
        }

        @PostMapping("/signout")
        public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok()
            .body(new MessageResponse("You've been signed out!"));

        }

}
