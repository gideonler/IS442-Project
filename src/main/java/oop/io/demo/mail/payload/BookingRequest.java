package oop.io.demo.mail.payload;

import javax.validation.constraints.NotBlank;

public class BookingRequest {
    @NotBlank
	private String email;
	@NotBlank
	private String loanId;
	/* 
	@NotBlank
	private String corpPassNumber;
	*/
	@NotBlank
	private String template;
	private String attachment;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	/* 
	public String getCorpPassNumber() {
		return corpPassNumber;
	}
	public void setCorpPassNumber(String corpPassNumber) {
		this.corpPassNumber = corpPassNumber;
	}
	*/
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}

