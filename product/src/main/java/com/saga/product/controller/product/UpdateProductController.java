package com.saga.product.controller.product;

import com.saga.dto.response.ResponseError;
import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.request.UpdateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.UpdateProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponse(
            responseCode = "200",
            description = "Get product by id successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class

                    )
            )
    )
    @PutMapping
    public ResponseEntity<ResponseSuccess<ProductResponse>> update(@RequestBody UpdateProductRequest updateProductRequest) {
        return execute(updateProductService.update(updateProductRequest), 200);
    }
}
