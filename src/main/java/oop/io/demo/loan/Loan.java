package oop.io.demo.loan;


import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;


import oop.io.demo.pass.Pass;

public class Loan {

    private Date loanDate;
    @CreatedDate
    private Date bookingMadeDate;
    private String attractionName;

    @CreatedBy
    private String userEmail;
    private String loanId;
    private String name;
    private int contactNo;

    private LOANSTATUS status;

    

    //constructor with attributes required to create a new loan

    public Loan(){}


    public Loan(String userEmail, Date loanDate, String attractionName) {
    
        this.loanDate = loanDate;//the date where the user is making the booking
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.loanId=this.loanDate+this.userEmail;
   
    
    }

    public String getPassNo(){
        Pass newpassno=new Pass();
        return newpassno.getPassNo();
    }

    
    public Date getLoanDate() {
        return loanDate;
    }

    public Date getBookingMadeDate() {
        return bookingMadeDate;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public String getLoanID(){
        return loanId;
    }

    public String getName(){
        return name;
    }

    public int getContactNo(){
        Pass p=new Pass();
       
        return contactNo;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
    public void setBookingMadeDate(Date bookingMadeDate) {
        this.bookingMadeDate = bookingMadeDate;
    }
    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserEmail() {
        return userEmail;
    }

    public LOANSTATUS getStatus() {
        return status;
    }
    public void setStatus(LOANSTATUS status) {
        this.status = status;
    }


}
