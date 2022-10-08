package oop.io.demo.pass;
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassRepository extends MongoRepository<Pass, String> {
    Pass findPassByPassId(String passID);
    Optional<List<Pass>> findPassesByPlaceOfInterest(String placeOfInterest);
}

