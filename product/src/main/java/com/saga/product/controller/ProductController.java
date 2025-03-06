package com.saga.product.controller;

import com.saga.product.dto.request.CreateProductRequest;
import com.saga.product.dto.request.UpdateProductRequest;
import com.saga.product.entity.Product;
import com.saga.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return new ResponseEntity<>(productService.list(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.create(createProductRequest), HttpStatusCode.valueOf(201));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(productService.update(updateProductRequest), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatusCode.valueOf(200));
    }
}
