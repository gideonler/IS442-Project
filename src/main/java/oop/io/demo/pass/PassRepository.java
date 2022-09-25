package oop.io.demo.pass;
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassRepository extends MongoRepository<Pass, String> {
    Optional<Pass> findByPassName(String passName);

    boolean existsByPassName(String email);
}