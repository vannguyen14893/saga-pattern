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

@Service
@RequiredArgsConstructor
public class UpdateProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ConvertProductResponseService convertProductResponseService;

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
