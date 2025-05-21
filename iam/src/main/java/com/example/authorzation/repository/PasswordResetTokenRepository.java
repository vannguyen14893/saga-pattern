package com.example.authorzation.repository;

import com.example.authorzation.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing PasswordResetToken entities.
 * Provides CRUD operations and custom queries for password reset functionality.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
    /**
     * Finds a PasswordResetToken entity by its token value.
     *
     * @param token the token to search for
     * @return the matching PasswordResetToken entity or null if not found
     */
    PasswordResetToken findByToken(String token);
}
