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
import oop.io.demo.mail.EmailSender;
import oop.io.demo.mail.payload.CollectedRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/passstatus")
public class PassGOController {
    private final EmailSender emailSender;
    
    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    private final LoanRepository loanRepository;

    public PassGOController(PassRepository passRepository, AttractionRepository attractionRepository, LoanRepository loanRepository, EmailSender emailSender) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
        this.loanRepository = loanRepository;
        this.emailSender = emailSender;
    }

    @PutMapping("/change")
    public ResponseEntity setPassStatusToInOffice(@RequestBody Map<String, String> loanIdAndStatusMap) {
        String loanId = loanIdAndStatusMap.get("loanId");
        PASSSTATUS passStatus = PASSSTATUS.valueOf(loanIdAndStatusMap.get("passStatus"));
        Loan loan = loanRepository.findByLoanId(loanId);
        if(loan==null) return ResponseEntity.badRequest().body("Loan not found!");
        String passId = loan.getAttractionName()+loan.getPassNo();
        if(passStatus.toString().equals("ONLOAN")){
            try {
                CollectedRequest collected = new CollectedRequest(loan.getUserEmail());
                emailSender.sendCollectedMessage(collected);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Email is not sent.");
            }
        }
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, passStatus);
        return responseEntity;
    }
    
}