package com.saga.order.repository;

import com.saga.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing OrderDetail entities.
 * Extends JpaRepository to inherit basic CRUD operations and pagination functionality.
 * The repository handles persistence operations for OrderDetail entities with Long type IDs.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
