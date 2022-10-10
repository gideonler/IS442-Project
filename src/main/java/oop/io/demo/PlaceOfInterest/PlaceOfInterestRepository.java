package oop.io.demo.PlaceOfInterest;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceOfInterestRepository extends MongoRepository<PlaceOfInterest, String> {
    PlaceOfInterest findByPlaceOfInterestName(String placeOfInterestName);
}
