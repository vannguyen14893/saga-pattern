package com.saga.product.service.product;

import com.saga.dto.enums.ActionType;
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

@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductAdapterProducer productAdapterProducer;
    private final ConvertProductResponseService convertProductResponseService;

    public ProductResponse create(CreateProductRequest createProductRequest) {
        Product product = new Product();
        Category category = categoryRepository.findById(createProductRequest.categoryId()).orElseThrow(() -> new NullPointerException("category not found"));
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
