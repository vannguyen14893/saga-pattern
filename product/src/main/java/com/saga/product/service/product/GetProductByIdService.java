package com.saga.product.service.product;

import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetProductByIdService {
    private final ProductRepository productRepository;
    private final ConvertProductResponseService convertProductResponseService;

    public ProductResponse findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return convertProductResponseService.convertToProductResponse(product.orElseThrow(() -> new NotFoundExceptionHandler("Product")));
    }
}
