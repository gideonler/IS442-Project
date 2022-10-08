package oop.io.demo.pass;
import java.util.*;

import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.SequenceGeneratorService;
import oop.io.demo.auth.security.jwt.JwtUtils;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pass")
public class PassController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final PassRepository repository;

    private SequenceGeneratorService sequenceGenerator;

    public PassController(PassRepository passRepository, SequenceGeneratorService sequenceGenerator) {
        this.repository = passRepository;
        this.sequenceGenerator = sequenceGenerator;
    }
  
    @GetMapping("/passes/{PlaceOfInterest}")
    public ResponseEntity<List<Pass>> getAvailablePassesByPlaceOfInterest(@PathVariable("placeofinterest") String placeOfInterest) {
        List<Pass> passes = new PassService(repository).getAvailablePassesByPlaceOfInterest(placeOfInterest);
        return ResponseEntity.ok(passes);
    }
    
    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getAllPasses() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PostMapping("/newpasses")
    public ResponseEntity createPasses(@RequestBody PassRequest passRequest) {
        int noOfPasses = passRequest.getNoOfPasses();
        int maxNoGuest = passRequest.getMaxNoGuest();
        System.out.println(noOfPasses);
        String status = passRequest.getPassStatus().toString().toUpperCase();
        PASSSTATUS passStatus = PASSSTATUS.valueOf(status);
        String type = passRequest.getPassType().toString().toUpperCase();
        PASSTYPE passType = PASSTYPE.valueOf(type);
        String placeOfInterest = passRequest.getPlaceOfInterest();
        double replacementFee = passRequest.getReplacementFee();
        for(int i = 0; i<noOfPasses; i++){
            Pass pass = new Pass();
            pass.setPassID(sequenceGenerator.generateSequence(Pass.SEQUENCE_NAME));
            pass.setMaxNoGuest(maxNoGuest);
            pass.setPassStatus(passStatus);
            pass.setPassType(passType);
            pass.setPlaceOfInterest(placeOfInterest);
            pass.setReplacementFee(replacementFee);
            repository.save(pass);
        }
        return ResponseEntity.ok("Uploaded");
    }

    
    @DeleteMapping("/deactivatepass")
    public ResponseEntity deactivatePass(@RequestBody String passId) {
        new PassService(repository).changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return ResponseEntity.ok("Deactivated");
    } 
}
