package com.saga.inventory.repository;

import com.saga.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Long productId);

    List<Inventory> findAllByProductIdIn(List<Long> productId);
}
