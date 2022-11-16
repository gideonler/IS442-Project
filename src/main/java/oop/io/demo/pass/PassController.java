/**
 * Contains endpoints for Staff and Admins to get passes
 * 1. Get all passes /pass/passes
 * 2. Get Pass by id /pass/{passid}
 * 3. Get Pass by attraction /pass/passes/{attractionname}
 * 4. Get all passes by status /pass/passes/status
 */

package oop.io.demo.pass;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import oop.io.demo.attraction.AttractionRepository;
import oop.io.demo.user.UserPublicDetails;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pass")
public class PassController {

    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    public PassController(PassRepository passRepository, AttractionRepository attractionRepository) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
    }

    //GET all passes
    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getAllPasses() {
        return ResponseEntity.ok(repository.findAll());
    }

    //GET pass by id
    @GetMapping("/{passid}")
    public ResponseEntity<Optional<Pass>> getPassDetails(@PathVariable("passid") String passId) {
        return ResponseEntity.ok(repository.findById(passId));
    }
  
    //GET passes by attraction
    @GetMapping("/passes/{attraction}")
    public ResponseEntity getAvailablePassesByAttraction(@PathVariable("attraction") String attraction) {
        PassService passService = new PassService(repository, attractionRepository);
        return ResponseEntity.ok(passService.getPassesByAttraction(attraction));
    }


    //GET passes by pass status
    @GetMapping("/passes/status")
    public ResponseEntity<List<Pass>> getPassesByPassStatus(@RequestParam(value="status") String passStatus) {
        PASSSTATUS status = PASSSTATUS.valueOf(passStatus.toUpperCase());
        return ResponseEntity.ok(repository.findByPassStatus(status).get());
    }
    
}
