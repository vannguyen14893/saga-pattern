package com.saga.product.controller.product;

import com.saga.dto.response.ResponseError;
import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.request.CreateProductRequest;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.CreateProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })

    @PostMapping
    public ResponseEntity<ResponseSuccess<ProductResponse>> create(@RequestBody @Valid
                                                                       CreateProductRequest createProductRequest) {
        return execute(createProductService.create(createProductRequest), 201);
    }
}
