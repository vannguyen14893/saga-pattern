package com.saga.product.controller.product;

import com.saga.dto.response.ResponseSuccess;
import com.saga.product.dto.response.ProductResponse;
import com.saga.product.service.product.GetListProductService;
import com.saga.response.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class GetListProductController extends BaseController {
    private final GetListProductService getListProductService;

    @Operation(
            summary = "Get list product",
            description = "Get list product",
            tags = {"product"}
    )
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<ProductResponse>>> list() {
        return execute(getListProductService.list(), 200);
    }

}
