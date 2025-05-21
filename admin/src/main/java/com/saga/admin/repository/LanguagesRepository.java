package com.saga.admin.repository;

import com.saga.admin.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Language entities.
 * Provides CRUD operations and custom queries for Language data access.
 */
public interface LanguagesRepository extends JpaRepository<Languages, Long> {
    /**
     * Retrieves all active language entities from the database.
     *
     * @return a List of active Language entities
     */
    List<Languages> findAllByActiveIsTrue();
}
