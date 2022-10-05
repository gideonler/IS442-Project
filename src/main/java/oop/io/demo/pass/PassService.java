package oop.io.demo.pass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PassService {

    private final PassRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public PassService(PassRepository passRepository){
        this.repository = passRepository;
    }

    public void createPass(PassRequest createPassRequest){
        Pass p = new Pass();
        p.setMaxNoGuest(createPassRequest.getMaxNoGuest());
        p.setPassStatus(createPassRequest.getPassStatus());
        p.setPassType(createPassRequest.getPassType());
        p.setPlaceOfInterest(createPassRequest.getPlaceOfInterest());
        p.setReplacementFee(createPassRequest.getReplacementFee());
    }

    public void changePassStatus(String passId, PASSSTATUS passStatus){
        Pass p = repository.GetPassById(passId);
        p.setPassStatus(passStatus);
    }

    public List<Pass> getPassesByAttraction(String attractionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("attractionname").is(attractionName));
        return mongoTemplate.find(query, Pass.class);
    }

    public List<Pass> getAvailablePassesByAttraction(String attractionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("attractionname").is(attractionName));
        query.addCriteria(Criteria.where("status").is("INOFFICE"));
        return mongoTemplate.find(query, Pass.class);

    }

    // public List<Pass> getPassesByAttractionAndType(String attractionName, PASSTYPE passtype){

    // }

    // public List<Pass> getAvailablePassesByAttractionAndType(String attractionName, PASSTYPE passtype) {

    // }

}
