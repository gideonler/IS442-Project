package oop.io.demo.attraction;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.pass.PASSSTATUS;
import oop.io.demo.pass.Pass;
import oop.io.demo.pass.PassRepository;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/attraction")
public class AttractionController {
    private final AttractionRepository repository;

    private final PassRepository passRepository;

    public AttractionController(AttractionRepository attractionRepository, PassRepository passRepository) {
        this.repository= attractionRepository;
        this.passRepository = passRepository;
    }

    @GetMapping("/attractions")
    public ResponseEntity<List<Attraction>> getAllPlacesOfInterest() {
        List<Attraction> ePass= repository.findByPassType(PASSTYPE.ELECTRONICPASS);
        List<Attraction> pPass = repository.findByPassType(PASSTYPE.PHYSICALPASS);
        ePass.addAll(pPass);
        return ResponseEntity.ok(ePass);
    }

    @GetMapping("/{attraction}/details")
    public ResponseEntity getAttractionDetails(@PathVariable("attraction") String attractionName){
        Optional<Attraction> attraction = repository.findByAttractionName(attractionName);
        if(attraction.isPresent()) {
            return ResponseEntity.ok(attraction.get());
        }
        else {
            return ResponseEntity.badRequest().body("Attraction not found!");
        }
    }
    
    @PostMapping("/new")
    public ResponseEntity createAttraction(@RequestBody AttractionRequest attractionRequest){
        String attractionName = attractionRequest.getAttraction();
        double replacementFee = attractionRequest.getReplacementFee();
        PASSTYPE passtype = PASSTYPE.valueOf(attractionRequest.getPassType().toUpperCase());
        Attraction attraction = new Attraction(attractionName,replacementFee,passtype);
        return ResponseEntity.ok(repository.save(attraction));
    }

    @PutMapping("/{attraction}/edit")
    public ResponseEntity editAttraction(@PathVariable("attraction") String attractionName, @RequestBody AttractionRequest attractionRequest) {
        Optional<Attraction> _attraction = repository.findByAttractionName(attractionName);
        if(_attraction.isPresent()){
            Attraction attraction = _attraction.get();
            if(attractionRequest.getPassType()!=null) attraction.setPasstype(PASSTYPE.valueOf(attractionRequest.getPassType().toUpperCase()));
            if(attractionRequest.getAttraction()!=null) attraction.setAttractionName(attractionRequest.getAttraction());
            if(attractionRequest.getReplacementFee()!=0.0) attraction.setReplacementFee(attractionRequest.getReplacementFee());
            return ResponseEntity.ok(repository.save(attraction));
        } else {
            return ResponseEntity.badRequest().body("Attraction not found.");
        }
    }

    @PutMapping("/{attraction}/deactivate")
    public ResponseEntity deactivateAttraction(@PathVariable("attraction") String attractionName) {
        Optional<Attraction> _attraction = repository.findByAttractionName(attractionName);
        if(_attraction.isPresent()){
            Attraction attraction = _attraction.get();
            attraction.setActive(false);
            repository.save(attraction);
            List<Pass> passes = passRepository.findByAttractionName(attractionName).get();
            if(passes.isEmpty()) return ResponseEntity.ok("Deactivated Attraction. No passes to deactivate.");
            for(Pass p: passes){
                p.setPassStatus(PASSSTATUS.DEACTIVATED);
            }
            passRepository.saveAll(passes);
            return ResponseEntity.ok("Deactivated "+ attractionName + " attraction and all passes under "+ attractionName+".");
        } else {
            return ResponseEntity.badRequest().body("Attraction not found.");
        }
    }

    @PutMapping("/{attraction}/reactivate")
    public ResponseEntity reactivateAttraction(@PathVariable("attraction") String attractionName) {
        Optional<Attraction> _attraction = repository.findByAttractionName(attractionName);
        if(_attraction.isPresent()){
            Attraction attraction = _attraction.get();
            attraction.setActive(true);
            repository.save(attraction);
            List<Pass> passes = passRepository.findByAttractionName(attractionName).get();
            if(passes.isEmpty()) return ResponseEntity.ok("Reactivated Attraction. No passes to reactivate.");
            for(Pass p: passes){
                p.setPassStatus(PASSSTATUS.INOFFICE);
            }
            passRepository.saveAll(passes);
            return ResponseEntity.ok("Reactivated "+ attractionName + " attraction and all passes under "+ attractionName+".");
        } else {
            return ResponseEntity.badRequest().body("Attraction not found.");
        }
    }
}