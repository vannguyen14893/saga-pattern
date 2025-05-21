package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.service.product.DeleteProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling product deletion operations.
 * This controller provides endpoints for removing existing products from the system.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class DeleteProductController extends BaseController {
    private final DeleteProductService productService;

    @Tag(name = "Delete product", description = "Operations pertaining to delete product in the system")
    @Operation(
            summary = "Delete product",
            description = "Delete a new product based on the provided id",
            tags = {"product"}
    )
    /**
     * Deletes an existing product from the system.
     *
     * @param id the unique identifier of the product to delete
     * @return ResponseEntity containing the deleted product's ID wrapped in a success response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(productService.delete(id), "200");
    }
}
