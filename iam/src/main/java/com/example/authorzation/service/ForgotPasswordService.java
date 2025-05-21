package com.example.authorzation.service;

import com.example.authorzation.dto.ChangePasswordRequest;
import com.example.authorzation.entity.PasswordResetToken;
import com.example.authorzation.entity.User;
import com.example.authorzation.repository.PasswordResetTokenRepository;
import com.example.authorzation.repository.UserRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForgotPasswordService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    //private final SendMailService sendMailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PasswordResetToken create(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setId(UUID.randomUUID().toString());
        String token = UUID.randomUUID().toString();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(LocalDateTime.now());
        passwordResetToken.setUserId(user.getId());
        PasswordResetToken save = passwordResetTokenRepository.save(passwordResetToken);
        //sendMailService.sendMailForgotPassword(email, token);
        return save;
    }

    @Transactional
    public User save(ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest == null || changePasswordRequest.getToken() == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(changePasswordRequest.getToken());
        User user = userRepository.findById(passwordResetToken.getUserId()).orElseThrow(() -> new NotFoundExceptionHandler("404"));
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getPasswordNew()));
        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);
        return user;
    }

    @Transactional(readOnly = true)
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? "invalid_token"
                : isTokenExpired(passToken) ? "expired_token"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().plusDays(1).isBefore(LocalDateTime.now());
    }
}
