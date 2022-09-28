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

    private String id;
  
    private String email;
  
    @JsonIgnore
    private String password;
  
    //private Collection<? extends GrantedAuthority> authorities;
    GrantedAuthority authority;

    private boolean isVerified;

    public UserDetailImplementation(String id, String email, String password,
        GrantedAuthority authority, boolean isVerified) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.authority = authority;
      this.isVerified = isVerified;
    }
  
    public static UserDetailImplementation build(User user) {
        String uType = user.getUserType()==null? USERTYPE.STAFF.toString(): user.getUserType().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(uType);
  
        return new UserDetailImplementation(
            user.getUserId(),
            user.getEmail(),
            user.getPassword(), 
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
  
    public String getId() {
        return id;
    }
  
    public String getEmail() {
        return email;
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
  
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailImplementation user = (UserDetailImplementation) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String getUsername() {
        return email;
    }
}
