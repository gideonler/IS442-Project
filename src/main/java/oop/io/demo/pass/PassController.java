package oop.io.demo.pass;
import java.util.*;

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
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.auth.security.jwt.JwtUtils;
@CrossOrigin(maxAge = 3600)
@RestController
// @RequestMapping("/api")
public class PassController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    PassService passService;

    private final PassRepository repository;

    public PassController(PassRepository passRepository) {
        this.repository = passRepository;
    }
  
    @GetMapping("/passes/{AttractionName}")
    public ResponseEntity<List<Pass>> getAvailablePassesByAttractionName(@PathVariable("attractionname") String attractionName) {
        List<Pass> passes = passService.getAvailablePassesByAttraction(attractionName);
        return ResponseEntity.ok(passes);
    }
    
    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getAllPasses() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PostMapping("/newpasses")
    public ResponseEntity createPasses(@RequestBody PassRequest passRequest) {
        int noOfPasses = passRequest.getNoOfPasses();
        for(int i = 0; i<noOfPasses;i++){
            Pass pass = new Pass();
            pass.setMaxNoGuest(passRequest.getMaxNoGuest());
            pass.setPassStatus(passRequest.getPassStatus());
            pass.setPassType(passRequest.getPassType());
            pass.setPlaceOfInterest(passRequest.getPlaceOfInterest());
            pass.setReplacementFee(passRequest.getReplacementFee());
            repository.save(pass);
        }
        return ResponseEntity.ok("Uploaded");
    }

    
    @DeleteMapping("/deactivatepass")
    public ResponseEntity deactivatePass(@RequestBody String passId) {
        passService.changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return ResponseEntity.ok("Deactivated");
    } 
}
