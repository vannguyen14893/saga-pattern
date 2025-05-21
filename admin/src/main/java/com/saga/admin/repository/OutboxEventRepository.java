package com.saga.admin.repository;

import com.saga.admin.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing OutboxEvent entities.
 * Provides CRUD operations for OutboxEvent data access using Spring Data JPA.
 */
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, String> {
}
