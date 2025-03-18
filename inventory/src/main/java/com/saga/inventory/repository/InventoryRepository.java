package com.saga.inventory.repository;

import com.saga.inventory.entity.Inventory;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.stream.Stream;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Long productId);

    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    @Query("select i.productId,i.quantity from Inventory i where i.productId in :productIds ")
    Stream<Inventory> findAllByProductIdIn(List<Long> productIds);
}
