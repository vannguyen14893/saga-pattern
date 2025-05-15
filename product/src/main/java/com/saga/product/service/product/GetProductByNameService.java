package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByNameService {
    private final ProductRepository productRepository;

    private final ConvertProductResponseService convertProductResponseService;

    public ProductResponse findByName(String name) {
        Product product = productRepository.findByName(name);
        return convertProductResponseService.convertToProductResponse(product);
    }
}
