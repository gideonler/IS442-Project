package oop.io.demo.mail.payload;

import javax.validation.constraints.NotBlank;

public class ConfirmRequest {
    @NotBlank
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

