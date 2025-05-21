package com.saga.product.repository;

import com.saga.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Category entities in the database.
 * Provides standard CRUD operations and additional query methods for Category entities.
 * Extends JpaRepository to inherit basic database operations.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
