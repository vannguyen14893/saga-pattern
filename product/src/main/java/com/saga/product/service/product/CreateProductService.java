package com.saga.product.service.product;

import com.saga.dto.enums.ActionType;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import com.saga.product.adapter.producer.ProductAdapterProducer;
import com.saga.product.dto.request.CreateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Category;
import com.saga.product.entity.Product;
import com.saga.product.repository.CategoryRepository;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service responsible for creating new products in the system.
 * This service handles the product creation process, including:
 * - Validating and creating new product records
 * - Managing category associations
 * - Coordinating with inventory system through Kafka messaging
 */
@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductAdapterProducer productAdapterProducer;
    private final ConvertProductResponseService convertProductResponseService;

    /**
     * Creates a new product based on the provided request data.
     * This method:
     * - Validates the category existence
     * - Creates and persists the new product
     * - Initiates inventory creation through Kafka messaging
     * - Converts and returns the product response
     *
     * @param createProductRequest the request containing product creation data
     * @return ProductResponse containing the created product details
     * @throws NotFoundExceptionHandler if the specified category is not found
     */
    public ProductResponse create(CreateProductRequest createProductRequest) {
        Product product = new Product();
        Category category = categoryRepository.findById(createProductRequest.categoryId()).orElseThrow(() -> new NotFoundExceptionHandler("category not found"));
        product.setName(createProductRequest.name());
        product.setPrice(createProductRequest.price());
        product.setDescription(createProductRequest.description());
        product.setCreatedDate(LocalDateTime.now());
        product.setCategory(category);
        Product save = productRepository.save(product);
        productAdapterProducer.sendToInventory(save.getId(), createProductRequest.quantity(), ActionType.CREATE_NEW);
        return convertProductResponseService.convertToProductResponse(save);
    }

}
