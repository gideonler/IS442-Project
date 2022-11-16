package oop.io.demo.loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

public class Loan {

    private String loanDate;
    @Column(nullable = false)
    private String dueDate;

    private String attractionName;

    @CreatedBy
    private String userEmail;

    @Column(unique = true)
    @Id
    private String loanId;

    private String name;
    private String contactNo;
    private String passId;


    private LOANSTATUS status;

    // constructor with attributes required to create a new loan

    public Loan() {
    }

    public Loan(String userEmail, LocalDate loanDate, String attractionName) {
        this.loanId = userEmail;
        this.loanDate = loanDate.toString();// the date where the user is making the booking
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.status = LOANSTATUS.CONFIRMED;
    }

    public Loan(String userEmail, LocalDate loanDate, String attractionName, String loanId) {
        this.loanId = loanId;
        this.loanDate = loanDate.toString();// the date where the user is making the booking
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.status = LOANSTATUS.CONFIRMED;
        

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
    }