package oop.io.demo.auth;

import org.springframework.http.ResponseEntity;

import oop.io.demo.auth.payload.request.SignupRequest;
import oop.io.demo.auth.payload.response.MessageResponse;
import oop.io.demo.user.USERTYPE;
import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

public class AuthService {
    
    private final UserRepository repository;

    public AuthService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public ResponseEntity<?> signUpOneUser(SignupRequest signUpRequest) {
        // Create new user's account
        User user = new User(
                            signUpRequest.getName(),
                            signUpRequest.getEmail());

        user.setUserType(USERTYPE.STAFF);
        user.setVerified(false);
        repository.save(user);
        return ResponseEntity.ok(new MessageResponse("Please check email to complete registration"));
    }
}
