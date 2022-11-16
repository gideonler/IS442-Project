package oop.io.demo.user;

public class UserPublicDetails {
	private String email;

    private String contactNo;

	private String name;

    public UserPublicDetails(String email, String contactNo, String name) {
        this.email = email;
        this.contactNo = contactNo;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
