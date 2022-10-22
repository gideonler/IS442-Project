package oop.io.demo.pass;
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassRepository extends MongoRepository<Pass, String> {
    Pass findByPassId(String passID);
    Optional<List<Pass>> findByPlaceOfInterestName(String placeOfInterestName);
    Optional<List<Pass>> findByPassStatus(PASSSTATUS passStatus);
}

