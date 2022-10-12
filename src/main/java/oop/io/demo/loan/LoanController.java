package oop.io.demo.loan;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import oop.io.demo.pass.PassRequest;
import oop.io.demo.pass.PassService;
public class LoanController {
    //loan controller should:
    //have method endpoint: "newbooking" calls method in loanservice to make new booking
    ////access: both staff and admin can access to make booking for themself- userEmail automatically assigned based on their identity

    private LoanRepository repository;
    public String newBooking(Loan loan){
        repository.save(loan);
        return "Booking to " + loan.getAttractionName() + " made by " + loan.getUserEmail() + " has been added.";
    }

    // public String deleteBooking(String loanID){
    //     Loan loan = repository.findByLoanID(loanID);
    //     repository.delete(loan);
    //     return "Booking to " + loan.getAttractionName() + " made by " + loan.getUserEmail() + " has been deleted.";
    // }

    public String updateBooking(Loan loan){
        repository.save(loan);
        return "";

    }



    //have method endpoint: "cancelbooking" calls loanservice to cancel booking
    ////access: both staff and admin
    public String cancelBooking(){
        repository.
    }

    //have method to allow user to report loss of card (associated with booking?) endpoint: reportloss

    //have method endpoint: "loans" calls loanservice to retrieve loan for a user by email
    ////access: staff can only see their own but admin can see for any selected user


    //have method to retrieve all bookings made on a certain date for a certain attraction
    ////access: all because it is for calendar display- should this be under service then?
    
    
}
