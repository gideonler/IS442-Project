package oop.io.demo.user;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import oop.io.demo.auth.payload.request.PasswordRequest;

public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void enableUser(String email){
        Optional<User> _user = userRepository.findByEmail(email);
        if(_user.isPresent()) {
            User user= _user.get();
            user.setVerified(true);
            userRepository.save(user);
        }
    }
}
