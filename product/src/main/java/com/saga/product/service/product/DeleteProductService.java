package com.saga.product.service.product;

import com.saga.dto.enums.ActionType;
import com.saga.product.adapter.producer.ProductAdapterProducer;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductService {
    private final ProductRepository productRepository;
    private final ProductAdapterProducer productAdapterProducer;

    public Long delete(Long id) {
        productRepository.deleteById(id);
        productAdapterProducer.sendToInventory(id, 0, ActionType.DELETE);
        return id;
    }
}
