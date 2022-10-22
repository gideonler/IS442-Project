package oop.io.demo.auth.payload.request;

import javax.validation.constraints.NotBlank;

public class PasswordRequest {

    String token;

    String oldPassword;
    
    @NotBlank
    String password;
    @NotBlank
    String retypePassword;

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    
}
