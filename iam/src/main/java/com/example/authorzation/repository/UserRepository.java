package com.example.authorzation.repository;

import com.example.authorzation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * Provides CRUD operations and custom queries for user authentication and management.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a User entity by their phone number.
     *
     * @param phone the phone number to search for
     * @return the matching User entity or null if not found
     */
    User findByPhone(String phone);

    /**
     * Finds a User entity by their email address.
     *
     * @param email the email address to search for
     * @return the matching User entity or null if not found
     */
    User findByEmail(String email);

    /**
     * Finds a User entity by matching both phone number and OTP code.
     *
     * @param phone the phone number to search for
     * @param otp   the one-time password to verify
     * @return the matching User entity or null if not found
     */
    User findByPhoneAndOtp(String phone, Integer otp);
}
