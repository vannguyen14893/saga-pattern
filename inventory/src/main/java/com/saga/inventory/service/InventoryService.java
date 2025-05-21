package com.saga.inventory.service;

import com.saga.inventory.dto.request.CreateInventoryRequest;
import com.saga.inventory.dto.request.UpdateInventoryRequest;
import com.saga.inventory.entity.Inventory;
import com.saga.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing inventory operations.
 * Handles CRUD operations and inventory checks for products.
 */
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    /**
     * Retrieves inventory information for a specific product.
     *
     * @param productId the ID of the product to find
     * @return the Inventory entity if found
     */
    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    /**
     * Retrieves inventory information for multiple products.
     *
     * @param productIds list of product IDs to search for
     * @return list of Inventory entities for the specified product IDs
     */
    @Transactional(readOnly = true)
    public List<Inventory> findAllByProductIdIn(List<Long> productIds) {
//        try(Stream<Inventory> postStream = inventoryRepository.findAllByProductIdIn(productIds)) {
//            postStream.filter(item->);
//        }
        return inventoryRepository.findAllByProductIdIn(productIds).collect(Collectors.toList());
    }

    /**
     * Creates a new inventory record.
     *
     * @param createInventoryRequest the request containing inventory details
     * @return the created Inventory entity
     */
    public Inventory create(CreateInventoryRequest createInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(createInventoryRequest.quantity());
        inventory.setProductId(createInventoryRequest.productId());
        return inventoryRepository.save(inventory);
    }

    /**
     * Updates an existing inventory record based on order details.
     *
     * @param updateInventoryRequest the request containing updated inventory information
     * @return the updated Inventory entity
     */
    public Inventory update(UpdateInventoryRequest.OrderDetailRequest updateInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventory.getQuantity() - updateInventoryRequest.quantity());
        inventory.setProductId(updateInventoryRequest.productId());
        inventoryRepository.save(inventory);
        return inventory;
    }

    /**
     * Deletes an inventory record for a specific product.
     *
     * @param productId the ID of the product to delete inventory for
     * @return the ID of the deleted product
     */
    public Long deleteByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventoryRepository.delete(inventory);
        return productId;
    }
}
