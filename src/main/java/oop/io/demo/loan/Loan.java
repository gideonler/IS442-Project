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
    @LastModifiedDate
    private Date lastModifiedDate;
    private String attractionName;

    @CreatedBy
    private String userEmail;
    //what type should passNo be?
    private int passNo;
    private LOANSTATUS status;

    //constructor with attributes required to create a new loan
    public Loan(Date loanDate, Date bookingMadeDate, String attractionName, String userEmail) {
        Calendar cal = Calendar.getInstance();
        this.loanDate = loanDate;
        this.bookingMadeDate = bookingMadeDate;
        this.attractionName = attractionName;
        this.userEmail = userEmail;
        this.lastModifiedDate=cal.getTime();
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

    public int getPassNo() {
        return passNo;
    }
    public void setPassNo(int passNo) {
        this.passNo = passNo;
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
