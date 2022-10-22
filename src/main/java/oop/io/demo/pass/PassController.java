package oop.io.demo.pass;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oop.io.demo.placeOfInterest.PlaceOfInterestRepository;
@RestController
@RequestMapping("/pass")
public class PassController {

    private final PassRepository repository;

    private final PlaceOfInterestRepository placeOfInterestRepository;

    public PassController(PassRepository passRepository, PlaceOfInterestRepository placeOfInterestRepository) {
        this.repository = passRepository;
        this.placeOfInterestRepository = placeOfInterestRepository; 
    }

    @GetMapping("/{passid}")
    public ResponseEntity<Optional<Pass>> getPassDetails(@PathVariable("passid") String passId) {
        return ResponseEntity.ok(repository.findById(passId));
    }
  
    @GetMapping("/passes/{placeofinterest}")
    public ResponseEntity<List<Pass>> getAvailablePassesByPlaceOfInterest(@PathVariable("placeofinterest") String placeOfInterest) {
        List<Pass> passes = new PassService(repository, placeOfInterestRepository).getAvailablePassesByPlaceOfInterest(placeOfInterest);
        return ResponseEntity.ok(passes);
    }
    
    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getAllPasses() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/passes/status")
    public ResponseEntity<List<Pass>> getPassesByPassStatus(@RequestParam(value="status") String passStatus) {
        PASSSTATUS status = PASSSTATUS.valueOf(passStatus.toUpperCase());
        return ResponseEntity.ok(repository.findByPassStatus(status).get());
    }
    
    //for creating new passes for an existing attraction
    @PostMapping("/{placeofinterestname}/new")
    public ResponseEntity createPasses(@PathVariable("placeofinterestname") String placeOfInterestName, @RequestBody PassRequest passRequest) {
        PassService passService = new PassService(repository, placeOfInterestRepository);
        ResponseEntity r = passService.createPass(passRequest);
        return r;
    }
    
    @GetMapping("/{passid}/deactivate")
    public ResponseEntity deactivatePass(@PathVariable("passid") String passId) {
        ResponseEntity r = new PassService(repository, placeOfInterestRepository).changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return r;
    }

    @GetMapping("/{passid}/activate")
    public ResponseEntity activatePass(@PathVariable("passid") String passId) {
        ResponseEntity r = new PassService(repository, placeOfInterestRepository).changePassStatus(passId, PASSSTATUS.INOFFICE);
        return r;
    }
}
