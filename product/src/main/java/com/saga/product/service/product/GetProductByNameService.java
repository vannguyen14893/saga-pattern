package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for retrieving product information by name.
 * This service provides functionality to search and fetch product details using product names.
 */
@Service
@RequiredArgsConstructor
public class GetProductByNameService {
    private final ProductRepository productRepository;

    private final ConvertProductResponseService convertProductResponseService;

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product to find
     * @return ProductResponse containing the product details
     */
    public ProductResponse findByName(String name) {
        Product product = productRepository.findByName(name);
        return convertProductResponseService.convertToProductResponse(product);
    }
}
