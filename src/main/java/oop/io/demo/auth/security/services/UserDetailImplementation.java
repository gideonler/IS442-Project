package oop.io.demo.auth.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import oop.io.demo.user.USERTYPE;
import oop.io.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailImplementation implements UserDetails {
    private static final String serialVersionUID = "";

    private String userName;
  
    private String email;
  
    @JsonIgnore
    private String password;

    private String name;
  
    
    GrantedAuthority authority;

    private boolean isVerified;

    public UserDetailImplementation(String userName, String email, String password,
    String name, GrantedAuthority authority, boolean isVerified) {
      this.userName = userName;
      this.email = email;
      this.password = password;
      this.authority = authority;
      this.isVerified = isVerified;
      this.name = name;
    }
  
    public static UserDetailImplementation build(User user) {
        String uType = user.getUserType()==null? USERTYPE.STAFF.toString(): user.getUserType().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(uType);
  
        return new UserDetailImplementation(
            user.getUsername(),
            user.getEmail(),
            user.getPassword(), 
            user.getName(),
            authority,
            user.isVerified());
    }
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = Collections.singletonList(authority);
        return authorities;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    public String getName() {
        return name;
    }
  
    @Override
    public String getPassword() {
        return password;
    }
  
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
  
    @Override
    public boolean isEnabled() {
        return isVerified;
    }

    public String getEmail() {
        return email;
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailImplementation user = (UserDetailImplementation) o;
        return Objects.equals(userName, user.userName);
    }
    //this is here because it has to be overriden
    @Override
    public String getUsername() {
        return userName;
    }
}
