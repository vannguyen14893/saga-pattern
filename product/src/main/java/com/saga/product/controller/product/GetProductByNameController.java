package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetProductByNameService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class GetProductByNameController extends BaseController {
    private final GetProductByNameService getProductByNameService;

    @Operation(
            summary = "Find by product by name",
            description = "Find by product by name",
            tags = {"product"}
    )
    @Tag(name = "Update product", description = "Operations pertaining to update product in the system")
    @ApiResponse(
            responseCode = "200",
            description = "Get product by name successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class

                    )
            )
    )
    @GetMapping("/{name}")
    public ResponseEntity<ResponseSuccess<ProductResponse>> findByName(@Parameter(description = "ID of product to be deleted", required = true)
                                                                       @PathVariable String name) {
        return execute(getProductByNameService.findByName(name), 200);
    }

}
