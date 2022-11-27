package oop.io.demo.loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import oop.io.demo.pass.PASSSTATUS;

public class LoanResponse {
    private String loanDate;
    private String dueDate;
    private String attractionName;
    private String userEmail;
    private String loanId;
    private String name;
    private String contactNo;
    private String passId;
    private LOANSTATUS status;
    private PASSSTATUS passStatus;


    public LoanResponse(String loanDate, String dueDate, String attractionName, String userEmail, String loanId,
            String name, String contactNo, String passId, LOANSTATUS status, PASSSTATUS passStatus) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.loanId = loanId;
        this.name = name;
        this.contactNo = contactNo;
        this.passId = passId;
        this.status = status;
        this.passStatus = passStatus;
    }

    public void setLoanId() {
        this.loanId=loanDate.toString()+this.userEmail+passId;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate.toString();
    }

    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public LOANSTATUS getStatus() {
        return this.status;
    }

    public void setStatus(LOANSTATUS status) {
        this.status = status;
    }

    public void setPassId(String passId){
        this.passId = passId;
    }

    public LocalDate getLoanDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.loanDate, dateFormat);
    }

    public String getDueDate(){
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate){
        LocalDate tomorrow = dueDate.plusDays(1);
        this.dueDate=tomorrow.toString();
    }

    public String getAttractionName() {
        return this.attractionName;
    }

    public String getLoanID() {
        return this.loanId;
    }

    public String getName() {
        return this.name;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public String getPassId() {
        return this.passId;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
        }

    public PASSSTATUS getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PASSSTATUS passStatus) {
        this.passStatus = passStatus;
    }

    
}
