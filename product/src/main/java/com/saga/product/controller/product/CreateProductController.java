package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.request.CreateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.CreateProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling product creation operations.
 * This controller provides endpoints for creating new products in the system.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class CreateProductController extends BaseController {
    private final CreateProductService createProductService;

    @Operation(
            summary = "Create new product",
            description = "Creates a new product based on the provided request data",
            tags = {"product"}
    )
    /**
     * Creates a new product in the system.
     *
     * @param createProductRequest the request containing product creation data
     * @return ResponseEntity containing the created product details wrapped in a success response
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<ProductResponse>> create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return execute(createProductService.create(createProductRequest), "201");
    }
}
