package com.saga.admin.repository;

import com.saga.admin.entity.Translate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Translate entities.
 * Provides CRUD operations and other database interactions for Translate objects.
 * Extends JpaRepository to inherit common database operations.
 */
public interface TranslateRepository extends JpaRepository<Translate, Long> {
}
