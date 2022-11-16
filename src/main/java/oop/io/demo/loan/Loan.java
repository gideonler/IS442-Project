package oop.io.demo.loan;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.Column;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

public class Loan {

    private LocalDate loanDate;
    @Column(nullable = false)
    private LocalDate dueDate;

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
        this.loanDate = loanDate;// the date where the user is making the booking
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.status = LOANSTATUS.CONFIRMED;
    }

    public Loan(String userEmail, LocalDate loanDate, String attractionName, String loanId) {
        this.loanId = loanId;
        this.loanDate = loanDate;// the date where the user is making the booking
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.status = LOANSTATUS.CONFIRMED;
        

    }

    public void setLoanId() {
        this.loanId=loanDate.toString()+this.userEmail+passId;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
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
        return this.loanDate;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(LocalDate dueDate){
        LocalDate tomorrow = dueDate.plusDays(1);
        this.dueDate=tomorrow;
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