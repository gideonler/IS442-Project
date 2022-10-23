package oop.io.demo.auth.payload.response;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String username;
    private String jwt;
    private GrantedAuthority usertype;
	private String name;

    public JwtResponse(String jwt, String username, String name, GrantedAuthority usertype) {
		this.jwt = jwt;
        this.username = username;
		this.name = name;
		this.usertype = usertype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
