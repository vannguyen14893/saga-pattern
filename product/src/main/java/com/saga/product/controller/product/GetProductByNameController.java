package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetProductByNameService;
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
 * REST controller responsible for handling product search operations by name.
 * This controller provides endpoints for retrieving product information based on product names.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class GetProductByNameController extends BaseController {
    private final GetProductByNameService getProductByNameService;

    @Operation(
            summary = "Find by product by name",
            description = "Find by product by name",
            tags = {"product"}
    )
    @Tag(name = "Update product", description = "Operations pertaining to get product by name in the system")
    @GetMapping("/{name}")
    /**
     * Retrieves product information based on the provided product name.
     *
     * @param name the name of the product to search for
     * @return ResponseEntity containing the product details wrapped in a success response
     */
    public ResponseEntity<ResponseSuccess<ProductResponse>> findByName(@Parameter(description = "Name of product to be get info", required = true)
                                                                       @PathVariable String name) {
        return execute(getProductByNameService.findByName(name), "200");
    }

}
