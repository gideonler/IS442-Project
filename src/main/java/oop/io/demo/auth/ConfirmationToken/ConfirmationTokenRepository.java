package oop.io.demo.auth.ConfirmationToken;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String>{
    Optional<ConfirmationToken> findByToken(String token);
}
