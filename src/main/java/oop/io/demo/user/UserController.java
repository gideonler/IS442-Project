package oop.io.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.security.jwt.JwtUtils;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity findByEmail(@PathVariable("username") String username) {
        Optional<User> user= this.repository.findById(username);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.badRequest().body("User with username:"+ username +" was not found.");
        }
    }

    @GetMapping("/users/{userType}")
    public ResponseEntity findByUserType(@PathVariable("userType") USERTYPE userType) {
        Optional<List<User>> users= this.repository.findByUserType(userType);
        if(users.isPresent()){
            return ResponseEntity.ok(users);
        }
        else{
            return ResponseEntity.badRequest().body("No users with staff type: "+ userType +" wwere not found.");
        }
    }

    /*@PutMapping("/users/{username}")
    public ResponseEntity updateUser(@PathVariable("username") String username, @RequestBody User user) {
        Optional<User> user = this.repository.findById(username);
        if (user.isPresent()) {
            
        }
    }*/

    @DeleteMapping("/users/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        Optional<User> user = this.repository.findById(username);
        if(user.isPresent()){
            this.repository.deleteById(username);
            return ResponseEntity.ok("Successfully deleted.");
        }
        else {
            return ResponseEntity.badRequest().body("User with username: " + username + " was not found.");
        }
    }

} 
