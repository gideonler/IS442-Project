package oop.io.demo.pass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PassService {

    private final PassRepository repository;

    private MongoTemplate mongoTemplate;
    
    public PassService(PassRepository passRepository){
        this.repository = passRepository;
    }

    public void createPass(PassRequest createPassRequest){
        Pass p = new Pass();
        p.setMaxNoGuest(createPassRequest.getMaxNoGuest());
        String passStatus= createPassRequest.getPassStatus().toString().toUpperCase();
        p.setPassStatus(PASSSTATUS.valueOf(passStatus));
        String passType = createPassRequest.getPassType().toString().toUpperCase();
        p.setPassType(PASSTYPE.valueOf(passType));
        p.setPlaceOfInterest(createPassRequest.getPlaceOfInterest());
        p.setReplacementFee(createPassRequest.getReplacementFee());
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
