package com.saga.inventory.service;

import com.saga.inventory.dto.request.CreateInventoryRequest;
import com.saga.inventory.dto.request.UpdateInventoryRequest;
import com.saga.inventory.entity.Inventory;
import com.saga.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public List<Inventory> findAllByProductIdIn(List<Long> productIds) {
        return inventoryRepository.findAllByProductIdIn(productIds);
    }

    public Inventory create(CreateInventoryRequest createInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(createInventoryRequest.quantity());
        inventory.setProductId(createInventoryRequest.productId());
        return inventoryRepository.save(inventory);
    }

    public Inventory update(UpdateInventoryRequest updateInventoryRequest) {
        Inventory inventory = inventoryRepository.findByProductId(updateInventoryRequest.productId());
        inventory.setQuantity(inventory.getQuantity() - updateInventoryRequest.quantity());
        inventory.setProductId(updateInventoryRequest.productId());
        return inventoryRepository.save(inventory);
    }

    public Long delete(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventoryRepository.delete(inventory);
        return productId;
    }
}
