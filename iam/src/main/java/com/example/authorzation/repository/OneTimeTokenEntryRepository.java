package com.example.authorzation.repository;

import com.example.authorzation.entity.OneTimeTokenEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing OneTimeTokenEntry entities.
 * Provides CRUD operations and custom queries for one-time token authentication.
 */
public interface OneTimeTokenEntryRepository extends JpaRepository<OneTimeTokenEntry, String> {
    /**
     * Finds a OneTimeTokenEntry entity by matching both token and username.
     *
     * @param token    the token to search for
     * @param username the username to search for
     * @return the matching OneTimeTokenEntry entity or null if not found
     */
    OneTimeTokenEntry findByTokenAndUsername(String token, String username);
}
