package oop.io.demo.pass;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.attraction.AttractionRepository;
import oop.io.demo.loan.Loan;
import oop.io.demo.loan.LoanRepository;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/passstatusupdate")
public class PassGOController {
    
    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    private final LoanRepository loanRepository;

    public PassGOController(PassRepository passRepository, AttractionRepository attractionRepository, LoanRepository loanRepository) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
        this.loanRepository = loanRepository;
    }

    @PutMapping("/passreturn")
    public ResponseEntity setPassStatusToInOffice(@RequestBody Map<String, String> loanIdMap) {
        String loanId = loanIdMap.get("loanId");
        Loan loan = loanRepository.findByLoanId(loanId);
        String passId = loan.getAttractionName()+loan.getPassNo();
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.INOFFICE);
        return responseEntity;
    }

    @PutMapping("/passloan")
    public ResponseEntity setPassStatusToOnLoan(@RequestBody Map<String, String> loanIdMap) {
        String loanId = loanIdMap.get("loanId");
        Loan loan = loanRepository.findByLoanId(loanId);
        String passId = loan.getAttractionName()+loan.getPassNo();
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.ONLOAN);
        return responseEntity;
    }
    
}
