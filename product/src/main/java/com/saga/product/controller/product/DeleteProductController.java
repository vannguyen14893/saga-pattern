package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.DeleteProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponse(
            responseCode = "200",
            description = "Delete product by id successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Long.class

                    )
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(productService.delete(id), 200);
    }
}
