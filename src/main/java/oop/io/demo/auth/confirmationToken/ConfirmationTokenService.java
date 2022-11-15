package oop.io.demo.auth.confirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public void setConfirmedAt(String token){
        Optional<ConfirmationToken> _confirmationToken = confirmationTokenRepository.findByToken(token);
        if(_confirmationToken.isPresent()){
            ConfirmationToken confirmationToken = _confirmationToken.get();
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            confirmationTokenRepository.save(confirmationToken);
        }
    }

    public ConfirmationToken getToken(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        return confirmationToken.get();
    }
}