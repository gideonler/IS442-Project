/**
 * Class with method to return UserDetails from User with specified email
 */

package oop.io.demo.auth.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oop.io.demo.user.User;
import oop.io.demo.user.UserRepository;

@Service
public class UserDetailServiceImplementation implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
  
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailImplementation.build(user);
    }
}
