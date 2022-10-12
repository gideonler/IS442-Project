package oop.io.demo.loan;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;

import oop.io.demo.pass.Pass;


public interface LoanRepository extends MongoRepository<Loan, String>{


    
    


    
}
