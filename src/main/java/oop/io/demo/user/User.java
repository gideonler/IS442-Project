package oop.io.demo.user;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {

    @Id
    private String userId;

    @Column(unique=true)
	private String email;

    @Column(unique =true)
    private String contactNo;

	private String name;
	private USERTYPE userType;
    private String password;

    private boolean isVerified;
    //private Set<RegistrationToken> tokens;

    private double outstandingFees;

    public User() {}

    public User(String name,  String email) {
        this.email= email;
        this.userId = email.split("@")[0];
        this.name = name;
        this.isVerified=false;
        this.userType = USERTYPE.STAFF;
        this.outstandingFees = 0.0;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public USERTYPE getUserType() {
        return userType;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(USERTYPE userType) {
        this.userType = userType;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public boolean isVerified() {
        return isVerified;
    }


    public double getOutstandingFees() {
        return outstandingFees;
    }

    public void setOutstandingFees(double outstandingFees) {
        this.outstandingFees = outstandingFees;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    


}