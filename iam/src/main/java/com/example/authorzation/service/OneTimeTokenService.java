package com.example.authorzation.service;

import com.example.authorzation.entity.OneTimeTokenEntry;
import com.example.authorzation.entity.User;
import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import com.example.authorzation.repository.OneTimeTokenEntryRepository;
import com.example.authorzation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OneTimeTokenService {
    private final OneTimeTokenEntryRepository oneTimeTokenEntryRepository;
    private final UserRepository userRepository;
    private static final int TOKEN_LENGTH = 16;
    private static final long TOKEN_VALIDITY_MINUTES = 10;

    public String generateToken(String username) {
        String token = generateSecureToken();
        OneTimeTokenEntry oneTimeTokenEntry = new OneTimeTokenEntry(UUID.randomUUID().toString(), username, token, LocalDateTime.now());
        oneTimeTokenEntryRepository.save(oneTimeTokenEntry);
        return token;
    }

    public void validateToken(String token, String username) {
        OneTimeTokenEntry oneTimeTokenEntry = oneTimeTokenEntryRepository.findByTokenAndUsername(token, username);
        if (oneTimeTokenEntry == null) throw new AuthenticationExceptionHandler("password.valid");
        if (LocalDateTime.now().isAfter(oneTimeTokenEntry.getCreatedAt().plusMinutes(TOKEN_VALIDITY_MINUTES))) {
            oneTimeTokenEntryRepository.delete(oneTimeTokenEntry);
            throw new AuthenticationExceptionHandler("token_expired");
        }
        User user = userRepository.findByPhone(username);
        user.setEnabled(true);
        userRepository.save(user);
        oneTimeTokenEntryRepository.delete(oneTimeTokenEntry);
    }

    private String generateSecureToken() {
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int randomChar = random.nextInt(62);
            if (randomChar < 10) {
                token.append(randomChar);
            } else if (randomChar < 36) {
                token.append((char) (randomChar - 10 + 'A'));
            } else {
                token.append((char) (randomChar - 36 + 'a'));
            }
        }

        return token.toString();
    }

}
