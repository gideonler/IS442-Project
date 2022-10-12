package oop.io.demo.loan;

import org.springframework.data.mongodb.core.MongoTemplate;

import oop.io.demo.pass.PASSSTATUS;
import oop.io.demo.pass.Pass;

import java.util.*;
public class LoanService {
    //loan service should:
    //have method be able to change pass status (when pass is made available)
    private final LoanRepository repository;

    private MongoTemplate mongoTemplate;
    
    public LoanService(LoanRepository loanRepository){
        this.repository = loanRepository;
    }


    
    //method to allow user to make booking
    //Display the list of passes for that day
    //filter based on the type of pass looking to be booked
    public void newBooking(String placeOfInterest, PassRequest createPassRequest){

        PASSSTATUS passStatus;
        if( passStatus== PASSSTATUS.INOFFICE){
            Loan loan = new Loan(null, null, placeOfInterest, placeOfInterest);
            String passNo = createPassRequest.getPassNo();
            loan.getLoanDate()
            repository.save(pass);

        }
        else{
            System.out.println("Pass not available");
        }

    }




    //method to allow user to cancel booking 


    //method to retrieve all loans under a certain user (using email) 
    

}
