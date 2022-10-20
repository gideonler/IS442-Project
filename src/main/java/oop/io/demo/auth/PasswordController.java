/*package oop.io.demo.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.confirmationToken.ConfirmationTokenRepository;
import oop.io.demo.auth.payload.request.PasswordRequest;
import oop.io.demo.auth.security.jwt.JwtUtils;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;
    
    public PasswordController (UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository) {
        this.userRepository= userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody String email){
        AuthService authService = new AuthService(userRepository, confirmationTokenRepository);
        authService.sendForgotPasswordEmail(email);
    }

    @GetMapping("/change")
    public ResponseEntity<?> changePassword(@RequestParam(required= false), String token, RedirectAttributes redirectAttributes) {

    }

    @PostMapping("/change")
    public ResponseEntity<?> changePassword(PasswordRequest passwordRequest) {
        
    }
}*/