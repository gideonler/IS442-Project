/**
 * Contains one endpoint for the General Office to change the status of a pass when collected/ returned
 * 1. Update pass status upon collection to ONLOAN or INOFFICE /passstatus/change
 */

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
@RequestMapping("/passstatus")
public class PassGOController {
    
    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    private final LoanRepository loanRepository;

    public PassGOController(PassRepository passRepository, AttractionRepository attractionRepository, LoanRepository loanRepository) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
        this.loanRepository = loanRepository;
    }

    @PutMapping("/change")
    public ResponseEntity setPassStatusToInOffice(@RequestBody Map<String, String> loanIdAndStatusMap) {
        String loanId = loanIdAndStatusMap.get("loanId");
        PASSSTATUS passStatus = PASSSTATUS.valueOf(loanIdAndStatusMap.get("passStatus"));
        Loan loan = loanRepository.findByLoanId(loanId);
        String passId = loan.getAttractionName()+loan.getPassNo();
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, passStatus);
        return responseEntity;
    }
    
}