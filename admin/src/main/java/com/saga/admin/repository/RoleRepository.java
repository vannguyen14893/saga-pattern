package com.saga.admin.repository;

import com.saga.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Role entities.
 * Provides CRUD operations and other database interactions for Role objects.
 * Extends JpaRepository to inherit common database operations.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
