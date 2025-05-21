package com.saga.product.controller.product;

import com.saga.dto.response.PaginationResult;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetListProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling product listing operations.
 * This controller provides endpoints for retrieving lists of products from the system.
 */
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class GetListProductController extends BaseController {
    private final GetListProductService getListProductService;

    @Operation(summary = "Get list product", description = "Get list product", tags = {"product"})
    /**
     * Retrieves a paginated list of all products in the system.
     *
     * @return ResponseEntity containing a paginated list of products wrapped in a PaginationResult
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<ProductResponse>>> list() {
        return execute(getListProductService.list(), 1);
    }
}
