package oop.io.demo.attraction;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AttractionRepository extends MongoRepository<Attraction, String>{
    Optional<Attraction> findByAttractionName(String attractionName);
    Optional<List<Attraction>> findByPassType(PASSTYPE passType);
}