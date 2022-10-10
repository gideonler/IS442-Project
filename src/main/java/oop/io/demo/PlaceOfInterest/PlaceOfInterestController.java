package oop.io.demo.PlaceOfInterest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import oop.io.demo.auth.security.jwt.JwtUtils;
import oop.io.demo.pass.PASSSTATUS;
import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/placeOfInterest")
public class PlaceOfInterestController {

    @Autowired
    AuthenticationManager autenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private final PlaceOfInterestRepository repository;

    private final PassRepository passRepository;

    public PlaceOfInterestController(PlaceOfInterestRepository placeOfInterestRepository, PassRepository passRepository) {
        this.repository= placeOfInterestRepository;
        this.passRepository = passRepository;
    }

    @GetMapping("/{placeofinterest}")
    public ResponseEntity<Optional<PlaceOfInterest>> getPlaceOfInterestDetails(@PathVariable("placeofinterest") String placeOfInterestName){
        return ResponseEntity.ok(repository.findById(placeOfInterestName));
    }
    
    @PostMapping("/new")
    public ResponseEntity createAttraction(@RequestBody PlaceOfInterestRequest placeOfInterestRequest){
        String placeOfInterestName = placeOfInterestRequest.getPlaceOfInterest();
        double replacementFee = placeOfInterestRequest.getReplacementFee();
        PASSTYPE passtype = PASSTYPE.valueOf(placeOfInterestRequest.getPasstype().toUpperCase());
        PlaceOfInterest placeOfInterest = new PlaceOfInterest(placeOfInterestName,replacementFee,passtype);
        return ResponseEntity.ok(repository.save(placeOfInterest));
    }

    @PutMapping("/deactivate/{placeofinterest}")
    public ResponseEntity deactiveAttraction(@PathVariable("placeofinterest") String placeOfInterestName) {
        PlaceOfInterest placeOfInterest = repository.findByPlaceOfInterestName(placeOfInterestName);
        placeOfInterest.setActive(false);
        List<Pass> passes = passRepository.findPassesByPlaceOfInterest(placeOfInterestName);
        for(Pass p: passes){
            p.setPassStatus(PASSSTATUS.DEACTIVATED);
            //to save this to repo
        }
        return ResponseEntity.ok("Deactivated "+ placeOfInterestName + " attraction and all passes under "+ placeOfInterestName+".");
    }
}
