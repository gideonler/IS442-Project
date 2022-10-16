package oop.io.demo.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import oop.io.demo.auth.ConfirmationToken.ConfirmationToken;
import oop.io.demo.auth.ConfirmationToken.ConfirmationTokenRepository;
import oop.io.demo.auth.ConfirmationToken.ConfirmationTokenService;
import oop.io.demo.auth.payload.request.SignupRequest;
import oop.io.demo.auth.payload.response.MessageResponse;
import oop.io.demo.mail.Email;
import oop.io.demo.mail.EmailService;
import oop.io.demo.user.USERTYPE;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;
import oop.io.demo.user.UserService;

public class AuthService {
    
    private final UserRepository repository;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private UserService userService;

    private ConfirmationTokenService confirmationTokenService;

    private EmailService emailService;

    public AuthService(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository) {
        this.repository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ResponseEntity<?> signUpOneUser(SignupRequest signUpRequest) {
        // Create new user's account
        User user = new User(
                            signUpRequest.getName(),
                            signUpRequest.getEmail());

        user.setUserType(USERTYPE.STAFF);
        user.setVerified(false);
        repository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
            );
        confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //send email
        String link = "localhost:8080/auth/confirm?token=" + token;
        Email mail = new Email();
        mail.setTo(user.getEmail());
        mail.setSubject("Complete your registration for the Singapore Sports School Employee Pass Booking website");
        //TODO: replace with email template; could create email builder service to integrate link and email
        mail.setContent("Please click on the following link to complete your registration: " + link);
        emailService= new EmailService();
        emailService.send(mail);
        return ResponseEntity.ok(new MessageResponse("Please check email to complete registration"));
    }

    @Transactional
    public String confirmToken(String token){
        confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if(confirmationToken==null) {
            throw new IllegalStateException("token not found!");
        }
        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already exists!");
        }
        confirmationTokenService.setConfirmedAt(token);

        userService = new UserService(repository);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }
}
