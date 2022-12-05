/**
 * Contains methods for
 * 1. Enabling/disabling user: enableDisableUser(String email, String enableOrDisable)
 * 2. Changing user type: changeUserType(Map<String, String> map, USERTYPE userType)
 */

package oop.io.demo.user;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    @Autowired
    PasswordEncoder encoder;
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Enable user or disable user and save user to repository
    public ResponseEntity enableDisableUser(String email, String enableOrDisable){
        Optional<User> _user = userRepository.findByEmail(email);
        if(_user.isPresent()) {
            User user= _user.get();
            boolean verified = enableOrDisable == "Enable";
            user.setVerified(verified);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("User not found!");
        }
    }

    public ResponseEntity changeUserType(Map<String, String> map, USERTYPE userType) {
        String email = (String) map.get("email");
        if(email==null) return ResponseEntity.badRequest().body("No email entered.");
        try {
            User user = this.userRepository.findByEmail(email).get();
            user.setUserType(userType);
            return ResponseEntity.ok(userRepository.save(user));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("User not found!");
        }
    }

    public ResponseEntity getPublicUserDetailsByEmail(String email) {
        try {
            User user= userRepository.findByEmail(email).get();
            UserPublicDetails publicUser = new UserPublicDetails(user.getEmail(), user.getContactNo(), user.getName());
            return ResponseEntity.ok(publicUser);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching user details. Please check that username is entered correctly.");
        }
    }

    public ResponseEntity updateOutstandingFee(String userEmail){
        Optional<User> _user = userRepository.findByEmail(userEmail);
        if(_user.isPresent()) {
            //Assuming that the user will pay back the full fees
            User user = _user.get();
            user.setOutstandingFees(0);
            userRepository.save(user);
            return ResponseEntity.ok("Penalty Fee payment has been made by " + user.getName()); 
        } else {
            return ResponseEntity.badRequest().body("Error fetching user details. Please check that username is entered correctly.");

        }
        
    }

}
