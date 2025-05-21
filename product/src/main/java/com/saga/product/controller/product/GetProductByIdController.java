package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetProductByIdService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling product retrieval operations by ID.
 * This controller provides endpoints for finding specific products in the system using their unique identifier.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class GetProductByIdController extends BaseController {
    private final GetProductByIdService getProductByIdService;

    @Operation(
            summary = "Find by product by id",
            description = "Find by product by id",
            tags = {"product"}
    )
    @Tag(name = "Get product", description = "Operations pertaining to retrieve products from the system")
    /**
     * Retrieves a specific product from the system using its ID.
     *
     * @param id the unique identifier of the product to retrieve
     * @return ResponseEntity containing the product details wrapped in a success response
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<ProductResponse>> findById(@Parameter(description = "ID of product to be retrieved", required = true)
                                                                     @PathVariable Long id) {
        return execute(getProductByIdService.findById(id), "200");
    }

}
