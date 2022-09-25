package oop.io.demo.loan;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Loan implements PassRepository, UserRepository{
    @Id
    private String LoanId;
    //help with dates: https://www.youtube.com/watch?v=xibt9JG1i54
    //more help with dates: https://www.youtube.com/watch?v=2JPJ1OOVT3E
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date LoanDate; //which date do u mean here? Do we need two dates?
    private String staffEmail; //is this GO's email?
    private LOANSTATUS loanStatus;
    private String PassNo;
    private String PassName;
    private int PassAmt;
    private String userEmail;

    public Loan (String userEmail, String PassName, Date LoanDate, int PassAmt){
        this.userEmail = userEmail;
        this.PassNo = PassNo;
        this.bookingStatus = BOOKINGSTATUS.TOBECOLLECTED; //this value is initiated for every new booking made.
    }

    public String GetBookingId() {
        return bookingId;
    }

    public BOOKINGSTATUS GetBookingStatus() {
        return bookingStatus;
    }

    public 
}
