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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import oop.io.demo.auth.security.jwt.JwtUtils;
import oop.io.demo.pass.PASSSTATUS;
import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/placeofinterest")
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

    @GetMapping("/all")
    public ResponseEntity getPlacesOfInterest() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{placeofinterest}/details")
    public ResponseEntity getPlaceOfInterestDetails(@PathVariable("placeofinterest") String placeOfInterestName){
        Optional<PlaceOfInterest> placeOfInterest = repository.findByPlaceOfInterestName(placeOfInterestName);
        if(placeOfInterest.isPresent()) {
            return ResponseEntity.ok(placeOfInterest.get());
        }
        else {
            return ResponseEntity.ok("Place Of Interest not found!");
        }
    }
    
    @PostMapping("/new")
    public ResponseEntity createAttraction(@RequestBody PlaceOfInterestRequest placeOfInterestRequest){
        String placeOfInterestName = placeOfInterestRequest.getPlaceOfInterest();
        double replacementFee = placeOfInterestRequest.getReplacementFee();
        PASSTYPE passtype = PASSTYPE.valueOf(placeOfInterestRequest.getPassType().toUpperCase());
        PlaceOfInterest placeOfInterest = new PlaceOfInterest(placeOfInterestName,replacementFee,passtype);
        return ResponseEntity.ok(repository.save(placeOfInterest));
    }

    @PutMapping("/{placeofinterest}/edit")
    public ResponseEntity editAttraction(@PathVariable("placeofinterest") String placeOfInterestName, @RequestBody PlaceOfInterestRequest placeOfInterestRequest) {
        Optional<PlaceOfInterest> _placeOfInterest = repository.findByPlaceOfInterestName(placeOfInterestName);
        if(_placeOfInterest.isPresent()){
            PlaceOfInterest placeOfInterest = _placeOfInterest.get();
            if(placeOfInterestRequest.getPassType()!=null) placeOfInterest.setPasstype(PASSTYPE.valueOf(placeOfInterestRequest.getPassType().toUpperCase()));
            if(placeOfInterestRequest.getPlaceOfInterest()!=null) placeOfInterest.setPlaceOfInterestName(placeOfInterestRequest.getPlaceOfInterest());
            if(placeOfInterestRequest.getReplacementFee()!=0.0) placeOfInterest.setReplacementFee(placeOfInterestRequest.getReplacementFee());
            return ResponseEntity.ok(repository.save(placeOfInterest));
        } else {
            return ResponseEntity.ok("Place of interest not found.");
        }
    }

    @PutMapping("/{placeofinterest}/deactivate")
    public ResponseEntity deactivateAttraction(@PathVariable("placeofinterest") String placeOfInterestName) {
        Optional<PlaceOfInterest> _placeOfInterest = repository.findByPlaceOfInterestName(placeOfInterestName);
        if(_placeOfInterest.isPresent()){
            PlaceOfInterest placeOfInterest = _placeOfInterest.get();
            placeOfInterest.setActive(false);
            List<Pass> passes = passRepository.findPassesByPlaceOfInterestName(placeOfInterestName);
            for(Pass p: passes){
                p.setPassStatus(PASSSTATUS.DEACTIVATED);
            }
            passRepository.saveAll(passes);
            repository.save(placeOfInterest);
            return ResponseEntity.ok("Deactivated "+ placeOfInterestName + " attraction and all passes under "+ placeOfInterestName+".");
        } else {
            return ResponseEntity.ok("Place of interest not found.");
        }
    }
}
