package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertProductResponseService {

    public ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCreatedDate());
    }
}
