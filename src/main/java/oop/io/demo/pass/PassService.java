package oop.io.demo.pass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import oop.io.demo.placeOfInterest.PlaceOfInterest;
import oop.io.demo.placeOfInterest.PlaceOfInterestRepository;


public class PassService {

    private final PassRepository repository;

    private PlaceOfInterestRepository placeOfInterestRepository;
    
    public PassService(PassRepository passRepository, PlaceOfInterestRepository placeOfInterestRepository){
        this.repository = passRepository;
        this.placeOfInterestRepository = placeOfInterestRepository;
    }

    public ResponseEntity createPass(PassRequest createPassRequest){
        String placeOfInterestName = createPassRequest.getPlaceOfInterestName();
        String passNo = createPassRequest.getPassNo();
        String passId = placeOfInterestName + passNo;
        if (placeOfInterestName==null || passNo ==null) {
            //can throw exception due to null value of field
            return ResponseEntity.ok("Place of Interest Name and Pass Number can't be empty");
        } else if (!placeOfInterestRepository.findByPlaceOfInterestName(placeOfInterestName).isPresent()) {
            //can throw exception due to nonexistence of entity
            return ResponseEntity.ok("Place of interest " + placeOfInterestName + " does not exist.");
        } else if (repository.findById(passId).isPresent()) {
            return ResponseEntity.ok("Pass with pass number: '" + passNo + "' for place of interest: '" + placeOfInterestName + "' already exists.");
        }
        Pass pass = new Pass(passNo, placeOfInterestName);
        return ResponseEntity.ok(repository.save(pass));
    }

    public ResponseEntity changePassStatus(String passId, PASSSTATUS passStatus){
        Optional<Pass> p = repository.findById(passId);
        if(!p.isPresent()) {
            return ResponseEntity.ok("Pass does not exist");
        }
        Pass pass = p.get();
        pass.setPassStatus(passStatus);
        repository.save(pass);
        return ResponseEntity.ok("Changed status of pass successfully to: " + passStatus.toString());
    }

    public List<Pass> getPassesByPlaceOfInterest(String placeOfInterest) {
        return repository.findByPlaceOfInterestName(placeOfInterest).get();
    }

    public List<Pass> getAvailablePassesByPlaceOfInterest(String placeOfInterest) {
        List<Pass> passes = repository.findByPlaceOfInterestName(placeOfInterest).get();
        if(passes==null) return null;
        List<Pass> passesByStatus = new ArrayList<>();
        for(Pass p : passes){
            if(p.getPassStatus()==PASSSTATUS.INOFFICE) passesByStatus.add(p);
        }
        return passesByStatus;
    }

    // public List<Pass> getPassesByPlaceOfInterestAndType(String placeOfInterest, PASSTYPE passtype){

    // }

    // public List<Pass> getAvailablePassesByPlaceOfInterestAndType(String placeOfInterest, PASSTYPE passtype) {

    // }

}
