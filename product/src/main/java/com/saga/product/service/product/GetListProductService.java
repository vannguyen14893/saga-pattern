package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for retrieving lists of products from the system.
 * This service provides functionality to fetch and convert product data into response DTOs.
 */
@Service
@RequiredArgsConstructor
public class GetListProductService {
    private final ProductRepository productRepository;
    private final ConvertProductResponseService convertProductResponseService;

    /**
     * Retrieves all products from the repository and converts them to ProductResponse DTOs.
     *
     * @return List of ProductResponse objects containing product information
     */
    public List<ProductResponse> list() {
        return productRepository.findAll().stream().map(convertProductResponseService::convertToProductResponse).toList();
    }
}
