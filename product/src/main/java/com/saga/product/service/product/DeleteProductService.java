package com.saga.product.service.product;

import com.saga.dto.enums.ActionType;
import com.saga.product.adapter.producer.ProductAdapterProducer;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling product deletion operations.
 * This service coordinates the deletion of products from the repository
 * and sends notifications to the inventory system.
 */

@Service
@RequiredArgsConstructor
public class DeleteProductService {
    private final ProductRepository productRepository;
    private final ProductAdapterProducer productAdapterProducer;

    /**
     * Deletes a product by its ID and notifies the inventory system.
     *
     * @param id the unique identifier of the product to delete
     * @return the ID of the deleted product
     */
    public Long delete(Long id) {
        productRepository.deleteById(id);
        productAdapterProducer.sendToInventory(id, 0, ActionType.DELETE);
        return id;
    }
}
