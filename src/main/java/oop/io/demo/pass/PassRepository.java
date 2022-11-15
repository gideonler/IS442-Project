package oop.io.demo.pass;
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassRepository extends MongoRepository<Pass, String> {
    Optional<Pass> findByPassId(String passId);
    Optional<List<Pass>> findByAttractionName(String attractionName);
    Optional<List<Pass>> findByPassStatus(PASSSTATUS passStatus);
}

