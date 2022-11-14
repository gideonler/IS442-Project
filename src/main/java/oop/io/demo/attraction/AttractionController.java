package oop.io.demo.attraction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/attraction")
public class AttractionController {
    
    private final AttractionRepository repository;

    public AttractionController(AttractionRepository attractionRepository) {
        this.repository= attractionRepository;
    }

    @GetMapping("/attractions")
    public ResponseEntity<List<Attraction>> getAllPlacesOfInterest() {
        Optional<List<Attraction>> ePass= repository.findByPassType(PASSTYPE.ELECTRONICPASS);
        Optional<List<Attraction>> pPass = repository.findByPassType(PASSTYPE.PHYSICALPASS);
        List<Attraction> attractions= new ArrayList<>();
        if(ePass.isPresent()) {
            attractions.addAll(ePass.get());
        }
        if(pPass.isPresent()) {
            attractions.addAll(pPass.get());
        }
        return ResponseEntity.ok(attractions);
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

}