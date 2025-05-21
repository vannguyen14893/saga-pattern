package com.saga.inventory.repository;

import com.saga.inventory.entity.Inventory;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.stream.Stream;

/**
 * Repository interface for managing Inventory entities in the database.
 * Provides methods for querying and manipulating inventory records.
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    /**
     * Finds an inventory record by its product ID.
     *
     * @param productId the ID of the product to search for
     * @return the Inventory entity if found, null otherwise
     */
    Inventory findByProductId(Long productId);

    /**
     * Retrieves inventory records for multiple product IDs using streaming.
     * Uses a fetch size hint of 25 for optimized memory usage during streaming.
     *
     * @param productIds list of product IDs to search for
     * @return a Stream of Inventory entities matching the provided product IDs
     */
    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    @Query("select i.productId,i.quantity from Inventory i where i.productId in :productIds ")
    Stream<Inventory> findAllByProductIdIn(List<Long> productIds);
}
