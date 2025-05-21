package com.example.authorzation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for password encoding setup.
 * Provides beans for password hashing and validation using BCrypt algorithm.
 */
@Configuration
public class PasswordConfig {
    /**
     * Creates a PasswordEncoder bean configured with BCrypt algorithm.
     * Uses a work factor of 12 for BCrypt hashing strength.
     *
     * @return DelegatingPasswordEncoder that defaults to BCrypt encoding
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder(12));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
