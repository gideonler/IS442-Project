package oop.io.demo.auth.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
     public boolean hasUsername(Authentication authentication, String username) {
        User u = (User) authentication.getPrincipal();
        if(u.getUsername().equals(username)) return true;
        return false;
    }
}
