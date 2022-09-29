package oop.io.demo.auth.payload.response;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String id;
	private String email;
    private String jwt;
    private GrantedAuthority usertype;
	private String name;

    public JwtResponse(String jwt, String string,  String email, String name, GrantedAuthority usertype) {
		this.jwt = jwt;
        this.id = string;
		this.email = email;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
