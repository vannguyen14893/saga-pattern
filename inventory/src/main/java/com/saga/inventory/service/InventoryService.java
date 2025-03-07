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

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Inventory> findAllByProductIdIn(List<Long> productIds) {
//        try(Stream<Inventory> postStream = inventoryRepository.findAllByProductIdIn(productIds)) {
//            postStream.filter(item->);
//        }
        return inventoryRepository.findAllByProductIdIn(productIds).collect(Collectors.toList());
    }

    public Inventory create(CreateInventoryRequest createInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(createInventoryRequest.quantity());
        inventory.setProductId(createInventoryRequest.productId());
        return inventoryRepository.save(inventory);
    }

    public Inventory update(UpdateInventoryRequest.OrderDetailRequest updateInventoryRequest) {
        Inventory inventory = inventoryRepository.findByProductId(updateInventoryRequest.productId());
        inventory.setQuantity(inventory.getQuantity() - updateInventoryRequest.quantity());
        inventory.setProductId(updateInventoryRequest.productId());
        inventoryRepository.save(inventory);
        return inventory;
    }

    public Long deleteByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventoryRepository.delete(inventory);
        return productId;
    }
}
