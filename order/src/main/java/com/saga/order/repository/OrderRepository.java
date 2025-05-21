package com.saga.order.repository;

import com.saga.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Order entities.
 * Extends JpaRepository to inherit basic CRUD operations and pagination functionality.
 * The repository handles persistence operations for Order entities with String type IDs.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
