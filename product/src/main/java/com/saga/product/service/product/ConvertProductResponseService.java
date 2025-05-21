package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for converting Product entities to ProductResponse DTOs.
 * This service provides methods to transform internal product data into client-friendly responses.
 */
@Service
@RequiredArgsConstructor
public class ConvertProductResponseService {

    /**
     * Converts a Product entity to a ProductResponse DTO.
     *
     * @param product the Product entity to convert
     * @return ProductResponse containing the product's data in DTO format
     */
    public ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCreatedDate());
    }
}
