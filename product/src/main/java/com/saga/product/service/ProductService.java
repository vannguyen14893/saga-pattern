package com.saga.product.service;

import com.saga.dto.enums.ActionType;
import com.saga.product.adapter.producer.ProductAdapterProducer;
import com.saga.product.dto.request.CreateProductRequest;
import com.saga.product.dto.request.UpdateProductRequest;
import com.saga.product.entity.Category;
import com.saga.product.entity.Product;
import com.saga.product.repository.CategoryRepository;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductAdapterProducer productAdapterProducer;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product create(CreateProductRequest createProductRequest) {
        Product product = new Product();
        Category category = categoryRepository.findById(createProductRequest.categoryId()).orElseThrow(() -> new NullPointerException("category not found"));
        product.setName(createProductRequest.name());
        product.setPrice(createProductRequest.price());
        product.setDescription(createProductRequest.description());
        product.setCreatedDate(LocalDateTime.now());
        product.setCategory(category);
        Product save = productRepository.save(product);
        productAdapterProducer.sendToInventory(save.getId(), createProductRequest.quantity(), ActionType.CREATE_NEW);
        return save;
    }

    public Product update(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.id()).orElseThrow(() -> new NullPointerException("product not found"));
        product.setName(updateProductRequest.name());
        product.setPrice(updateProductRequest.price());
        product.setDescription(updateProductRequest.description());
        product.setCreatedDate(LocalDateTime.now());
        Category category = categoryRepository.findById(updateProductRequest.categoryId()).orElseThrow(() -> new NullPointerException("category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Long delete(Long id) {
        productRepository.deleteById(id);
        productAdapterProducer.sendToInventory(id, 0, ActionType.DELETE);
        return id;
    }
}
