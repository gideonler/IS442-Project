package oop.io.demo.Booking;

import java.text.DateFormat;
import java.util.*;

public class Booking {

    // @Id ignore this for now
    private Long bookingId;
    private String staffEmail;
    private DateFormat useDate;
    private BookingStatus bookingStatus;
    private Long passNo;
    private String passType;

    public Booking(String staffEmail, DateFormat useDate, Long passNo, String passType) {
        this.staffEmail = staffEmail;
        this.useDate = useDate;
        this.passNo = passNo;
        this.passType = passType;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public void setUseDate(DateFormat useDate) {
        this.useDate = useDate;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPassNo(Long passNo) {
        this.passNo = passNo;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public DateFormat getUseDate() {
        return useDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Long getPassNo() {
        return passNo;
    }

    public String getPassType() {
        return passType;
    }

}

// To decide: if cancelled, will it still be stored?
public enum BookingStatus {
    // Active,
    // Cancelled,
    // Over
}
