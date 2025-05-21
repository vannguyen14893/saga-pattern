package com.saga.product.service.product;

import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service responsible for retrieving products by their ID.
 * This service provides functionality to fetch individual product details from the repository.
 */
@Service
@RequiredArgsConstructor
public class GetProductByIdService {
    private final ProductRepository productRepository;
    private final ConvertProductResponseService convertProductResponseService;

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the unique identifier of the product to retrieve
     * @return ProductResponse containing the product details
     * @throws NotFoundExceptionHandler if the product with the given ID is not found
     */
    public ProductResponse findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return convertProductResponseService.convertToProductResponse(product.orElseThrow(() -> new NotFoundExceptionHandler("Product")));
    }
}
