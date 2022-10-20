package oop.io.demo.pass;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;

import oop.io.demo.PlaceOfInterest.PlaceOfInterestRepository;


public class PassService {

    private final PassRepository repository;

    private MongoTemplate mongoTemplate;

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
        Query query = new Query();
        query.addCriteria(Criteria.where("placeofinterest").is(placeOfInterest));
        return mongoTemplate.find(query, Pass.class);
    }

    public List<Pass> getAvailablePassesByPlaceOfInterest(String placeOfInterest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("placeofinterest").is(placeOfInterest));
        query.addCriteria(Criteria.where("status").is("INOFFICE"));
        return mongoTemplate.find(query, Pass.class);

    }

    // public List<Pass> getPassesByPlaceOfInterestAndType(String placeOfInterest, PASSTYPE passtype){

    // }

    // public List<Pass> getAvailablePassesByPlaceOfInterestAndType(String placeOfInterest, PASSTYPE passtype) {

    // }

}
