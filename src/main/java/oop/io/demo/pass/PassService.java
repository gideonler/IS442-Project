package oop.io.demo.pass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import oop.io.demo.PlaceOfInterest.PASSTYPE;

public class PassService {

    private final PassRepository repository;

    private MongoTemplate mongoTemplate;
    
    public PassService(PassRepository passRepository){
        this.repository = passRepository;
    }

    public void createPass(String placeOfInterest, PassRequest createPassRequest){
        PASSSTATUS passStatus = PASSSTATUS.INOFFICE;
        Pass pass = new Pass();
        String passNo = createPassRequest.getPassNo();
        pass.setPassNo(passNo);
        String passId = passNo + placeOfInterest;
        pass.setPassID(passId);
        pass.setPassStatus(passStatus);
        pass.setPlaceOfInterest(placeOfInterest);
        repository.save(pass);
    }

    public void changePassStatus(String passId, PASSSTATUS passStatus){
        Pass p = repository.findPassByPassId(passId);
        p.setPassStatus(passStatus);
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
