package oop.io.demo.pass;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.SequenceGeneratorService;
import oop.io.demo.auth.security.jwt.JwtUtils;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pass")
public class PassController {

    //@Autowired
    //AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private final PassRepository repository;

    //remove if not needed
    private SequenceGeneratorService sequenceGenerator;

    public PassController(PassRepository passRepository, SequenceGeneratorService sequenceGenerator) {
        this.repository = passRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    @GetMapping("/{passid}")
    public ResponseEntity<Optional<Pass>> getPassDetails(@PathVariable("passid") String passId) {
        return ResponseEntity.ok(repository.findById(passId));
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
    
    //for creating new passes for an existing attraction
    @PostMapping("{placeOfInterest}/newpass")
    public ResponseEntity createPasses(@PathVariable("placeOfInterest") String placeOfInterest, @RequestBody PassRequest passRequest) {
        PassService passService = new PassService(repository);
        passService.createPasses(placeOfInterest, passRequest);
        return ResponseEntity.ok("Uploaded");
    }

    
    @PutMapping("/deactivatepass")
    public ResponseEntity deactivatePass(@RequestBody String passId) {
        new PassService(repository).changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return ResponseEntity.ok("Deactivated");
    }
}
