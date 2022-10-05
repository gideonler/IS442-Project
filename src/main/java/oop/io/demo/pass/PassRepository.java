package oop.io.demo.pass;
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassRepository extends MongoRepository<Pass, String> {
    List<Pass> GetPasses();
    Pass GetPassById(String id);
    List<Pass> GetPassesByAttractionName(String attractionName);
}

