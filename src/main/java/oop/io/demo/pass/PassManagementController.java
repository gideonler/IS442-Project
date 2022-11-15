/**
 * Contains endpoints for the ADMIN to manage passes
 * 1. Create new pass /passmanagement/new
 * 2. Deactivate pass /passmanagement/deactivate
 * 3. Activate pass /passmanagement/activate
 * 4. Edit Pass /passmanagement/{passId}/edit
 */

package oop.io.demo.pass;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import oop.io.demo.attraction.AttractionRepository;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/passmanagement")
public class PassManagementController {

    private final PassRepository repository;

    private final AttractionRepository attractionRepository;

    public PassManagementController(PassRepository passRepository, AttractionRepository attractionRepository) {
        this.repository = passRepository;
        this.attractionRepository = attractionRepository; 
    }
    
    //for creating new passes for an existing attraction
    @PostMapping("/new")
    public ResponseEntity createPass(@RequestBody PassRequest passRequest) {
        PassService passService = new PassService(repository, attractionRepository);
        ResponseEntity responseEntity = passService.createPass(passRequest);
        return responseEntity;
    }
    
    //to deactivate a pass by id
    @PutMapping("/deactivate")
    public ResponseEntity deactivatePass(@RequestBody Map<String, String> map) {
        String passId = map.get("passId");
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.DEACTIVATED);
        return responseEntity;
    }

    //to activate a pass by id
    @PutMapping("/activate")
    public ResponseEntity activatePass(@RequestBody Map<String, String> map) {
        String passId = map.get("passId");
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).changePassStatus(passId, PASSSTATUS.INOFFICE);
        return responseEntity;
    }

    //to edit pass with a certain id
    @PutMapping("/{passId}/edit")
    public ResponseEntity editPass(@PathVariable("passId") String passId, @RequestBody Map<String, String> map) {
        String newPassNo = map.get("passNo");
        ResponseEntity responseEntity = new PassService(repository, attractionRepository).editPass(passId, newPassNo);
        return responseEntity;
    }

}
