package com.saga.admin.repository;

import com.saga.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Menu entities.
 * Provides CRUD operations for Menu data access using Spring Data JPA.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
