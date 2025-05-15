package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetProductByIdService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Tag(name = "Delete product", description = "Operations pertaining to delete product in the system")
    @ApiResponse(
            responseCode = "200",
            description = "Get product by id successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class

                    )
            )
    )
    @GetMapping("/{id}")

    public ResponseEntity<ResponseSuccess<ProductResponse>> findById(@Parameter(description = "ID of product to be deleted", required = true)
                                                                     @PathVariable Long id) {
        return execute(getProductByIdService.findById(id), 200);
    }

}
