package oop.io.demo.loan;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;

import oop.io.demo.pass.Pass;

//by extending MongoRepository, we can use MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public interface LoanRepository extends MongoRepository<Loan, String>{

    
   
    ArrayList<Loan> findAllByEmail(String userEmail);
    ArrayList<Loan> findAllByAttraction(String attractionName);
    Loan findbyLoanID(String loanID);
    ArrayList<Loan> findAllByPassNo(int passNo);

    


    
}
