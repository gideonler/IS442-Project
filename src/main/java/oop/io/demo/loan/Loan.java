package oop.io.demo.loan;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class Loan {

    private Date loanDate;
    @CreatedDate
    private Date bookingMadeDate;
    private String attractionName;

    @CreatedBy
    private String userEmail;
    private String loanID;
    private LOANSTATUS status;

    

    //constructor with attributes required to create a new loan

    public Loan(){}


    public Loan(String userEmail, Date loanDate) {
    
        this.loanDate = loanDate;//the date where the user is making the booking
        this.bookingMadeDate = bookingMadeDate;
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.loanID=loanDate+userEmail;
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
