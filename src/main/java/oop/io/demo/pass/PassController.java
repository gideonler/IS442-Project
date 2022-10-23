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

import oop.io.demo.attraction.AttractionRepository;
@RestController
@RequestMapping("/pass")
public class PassController {

    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    public PassController(PassRepository passRepository, AttractionRepository attractionRepository) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
    }

    @GetMapping("/{passid}")
    public ResponseEntity<Optional<Pass>> getPassDetails(@PathVariable("passid") String passId) {
        return ResponseEntity.ok(repository.findById(passId));
    }
  
    @GetMapping("/passes/{attraction}")
    public ResponseEntity getAvailablePassesByAttraction(@PathVariable("attraction") String attraction) {
        List<Pass> passes = new PassService(repository, attractionRepository).getAvailablePassesByAttraction(attraction);
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
    @PostMapping("/{attractionname}/new")
    public ResponseEntity createPasses(@PathVariable("attractionname") String attractionName, @RequestBody PassRequest passRequest) {
        PassService passService = new PassService(repository, attractionRepository);
        ResponseEntity responseEntity = passService.createPass(passRequest);
        return responseEntity;
    }
    
    @GetMapping("/{passid}/deactivate")
    public ResponseEntity deactivatePass(@PathVariable("passid") String passId) {
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return responseEntity;
    }

    @GetMapping("/{passid}/activate")
    public ResponseEntity activatePass(@PathVariable("passid") String passId) {
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.INOFFICE);
        return responseEntity;
    }
}
