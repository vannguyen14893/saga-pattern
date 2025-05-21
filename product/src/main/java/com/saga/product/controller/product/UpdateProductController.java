package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.request.UpdateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.UpdateProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling product update operations.
 * This controller provides endpoints for modifying existing products in the system.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class UpdateProductController extends BaseController {
    private final UpdateProductService updateProductService;

    @Operation(
            summary = "Update product",
            description = "Update a product based on the provided request data",
            tags = {"product"}
    )
    @Tag(name = "Update product", description = "Operations pertaining to update product in the system")
    /**
     * Updates an existing product in the system.
     *
     * @param updateProductRequest the request containing product update data
     * @return ResponseEntity containing the updated product details wrapped in a success response
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<ProductResponse>> update(@RequestBody UpdateProductRequest updateProductRequest) {
        return execute(updateProductService.update(updateProductRequest), "200");
    }
}
