package oop.io.demo.placeOfInterest;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceOfInterestRepository extends MongoRepository<PlaceOfInterest, String> {
    Optional<PlaceOfInterest> findByPlaceOfInterestName(String placeOfInterestName);
}
