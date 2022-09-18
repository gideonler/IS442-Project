package oop.io.demo.login.payload.response;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String id;
	private String email;
    private String jwt;
    private GrantedAuthority usertype;

    public JwtResponse(String jwt, String string,  String email, GrantedAuthority usertype) {
		this.jwt = jwt;
        this.id = string;
		this.email = email;
		this.usertype = usertype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GrantedAuthority getUserType() {
		return usertype;
	}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
