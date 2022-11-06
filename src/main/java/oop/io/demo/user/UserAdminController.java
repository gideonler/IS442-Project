package oop.io.demo.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.security.jwt.JwtUtils;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usermanagement")
public class UserAdminController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository repository;

    public UserAdminController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    ////ADMIN ONLY ACCESS
    //Get all users
    //Access: only admin access
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(repository.findAll());
    }

    //Get all users by user type
    //Access: admin
    @GetMapping("/usersbytype")
    public ResponseEntity findByUserType(@RequestBody Map map) {
        USERTYPE userType = USERTYPE.valueOf(((String) map.get("userType")).toUpperCase());
        if(userType==null) return ResponseEntity.badRequest().body("Please enter a valid usertype.");
        try {
            List<User> users= this.repository.findByUserType(userType).get();
            return ResponseEntity.ok(users);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("No users with staff type: "+ (String) map.get("userType") +" were not found.");
        }
    }

    //Promote user to admin
    //Access: admin
    @PutMapping("/promote")
    public ResponseEntity makeUserAdmin(@RequestBody Map map) {
        String username = (String) map.get("username");
        if(username==null) return ResponseEntity.badRequest().body("No username entered.");
        try {
            User user = this.repository.findById(username).get();
            user.setUserType(USERTYPE.ADMIN);
            return ResponseEntity.ok(repository.save(user));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("User not found!");
        }
    }

    //Delete user with username {username}
    //Access: admin
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody Map map) {
        String username = (String) map.get("username");
        if(username==null) return ResponseEntity.badRequest().body("No username entered.");
        try {
            User user = this.repository.findById(username).get();
            repository.delete(user);
            return ResponseEntity.ok("Successfully deleted user.");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("User was not found.");
        }
        
    }

} 
