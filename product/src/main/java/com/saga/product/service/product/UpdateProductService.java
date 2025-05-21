package com.saga.product.service.product;

import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import com.saga.product.dto.request.UpdateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Category;
import com.saga.product.entity.Product;
import com.saga.product.repository.CategoryRepository;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service class responsible for handling product update operations.
 * This service provides functionality to update existing products in the system.
 */
@Service
@RequiredArgsConstructor
public class UpdateProductService {
    /**
     * Repository for managing product entities
     */
    private final ProductRepository productRepository;
    /**
     * Repository for managing category entities
     */
    private final CategoryRepository categoryRepository;
    /**
     * Service for converting product entities to response DTOs
     */
    private final ConvertProductResponseService convertProductResponseService;

    /**
     * Updates an existing product with new information.
     *
     * @param updateProductRequest DTO containing the updated product information
     * @return ProductResponse containing the updated product details
     * @throws NotFoundExceptionHandler if the product or category is not found
     */
    public ProductResponse update(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Product"));
        product.setName(updateProductRequest.name());
        product.setPrice(updateProductRequest.price());
        product.setDescription(updateProductRequest.description());
        product.setCreatedDate(LocalDateTime.now());
        Category category = categoryRepository.findById(updateProductRequest.categoryId()).orElseThrow(() -> new NotFoundExceptionHandler("Category"));
        product.setCategory(category);
        return convertProductResponseService.convertToProductResponse(productRepository.save(product));
    }
}
