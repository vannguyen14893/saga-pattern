package com.saga.admin.repository;

import com.saga.admin.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Permission entities.
 * Provides CRUD operations and other database interactions for Permission objects.
 * Extends JpaRepository to inherit common database operations.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
