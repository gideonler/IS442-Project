package oop.io.demo.auth.payload.request;

import javax.validation.constraints.NotBlank;

public class SignupRequest {
    @NotBlank
	private String email;

	@NotBlank
	private String name;

	public SignupRequest(String email, String name) {
		this.email = email;
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
