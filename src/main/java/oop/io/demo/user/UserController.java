/**
 * Contains all the endpoints for managing users accessible to STAFF AND ADMIN
 * 1. Get public user details for a specific user /user/userdetails
 * 2. Get user's own profile details /user/mydetails
 * 3. Edit profile /user/editprofile
 */

package oop.io.demo.user;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    private final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }
    
    ////ALL ACCESS
    //Get public user details by username
    @GetMapping("/userdetails")
    public ResponseEntity getPublicDetailsByEmail(@RequestBody Map<String, String> map) {
        UserService userService = new UserService(repository);
        return userService.getPublicUserDetailsByEmail(map.get("email"));
    }

    ////USER AND ADMIN ACCESS
    //Get user by username
    //Access: only admin and user with {username} can access
    @GetMapping("/mydetails")
    public ResponseEntity getMyDetails(@RequestParam("useremail") String userEmail) {
        try {
            User user= this.repository.findByEmail(userEmail).get();
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching user details. Please check that email is entered correctly.");
        }
    }

    ////USER ONLY ACCESS
    //Edit user profile details
    //Access: user
    @PutMapping("/editprofile")
    public ResponseEntity editProfile(@RequestParam("useremail") String userEmail, @RequestBody EditProfileRequest editProfileRequest/*, Principal principal*/) {
        //Authentication authentication = (Authentication) principal;
        //User u = (User) authentication.getPrincipal();
        try {
            User user = repository.findByEmail(userEmail).get();
            if(editProfileRequest.getContactNo()!=null) user.setContactNo(editProfileRequest.getContactNo());
            if(editProfileRequest.getName()!=null) user.setName(editProfileRequest.getName());
            return ResponseEntity.ok(repository.save(user));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

} 
