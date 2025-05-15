package com.saga.product.service.product;

import com.saga.product.dto.response.ProductResponse;
import com.saga.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListProductService {
    private final ProductRepository productRepository;
    private final ConvertProductResponseService convertProductResponseService;

    public List<ProductResponse> list() {
        return productRepository.findAll().stream().map(convertProductResponseService::convertToProductResponse).toList();
    }
}
