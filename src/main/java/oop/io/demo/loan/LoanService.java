package oop.io.demo.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import oop.io.demo.pass.PASSSTATUS;
import oop.io.demo.pass.Pass;

import java.util.*;

@Service
public class LoanService {

    //loan service should:
    //have method be able to change pass status (when pass is made available)
    private final LoanRepository repository;

    private MongoTemplate mongoTemplate;
    
    public LoanService(LoanRepository loanRepository){
        this.repository = loanRepository;
    }
    //loan service should:
    //have method be able to change pass status (when pass is made available)
    
    //method to allow user to make booking



    //method to allow user to cancel booking 

    public void newLoan(){
        PASSSTATUS passStatus = PASSSTATUS.INOFFICE;
        Loan loan = new Loan();
        loan.setEmail(null);
        loan.setStatus(null);
        loan.setBookingMadeDate(null);
        repository.save(loan);
    }






    // public String checkLoan(Loan loan){
    //     ArrayList<Loan> loanlist = repository.findBy(loan);
    //     Calendar cal = Calendar.getInstance();
    //     Date todaydate = cal.getTime();
    //     for (int i=0; i<loanlist.size(); i++){
    //         Loan loan = loanlist.get(i);
    //         Date checkdate = loan.getLoanDate();
    //         if (todaydate == checkdate){
    //             return "Booking exists on " + todaydate;
    //         } else {
    //             return "Booking does not exist on " + todaydate;
    //         } 
    //     } 
    //     return "";

    // }
 

    //method to allow user to make booking
    //Display the list of passes for that day
    //filter based on the type of pass looking to be booked





    




    //method to allow user to cancel booking 


    //method to retrieve all loans under a certain user (using email) 
    

}
