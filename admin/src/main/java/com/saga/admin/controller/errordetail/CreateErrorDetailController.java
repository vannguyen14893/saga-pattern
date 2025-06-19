package com.saga.admin.controller.errordetail;

import com.saga.admin.dto.request.errordetail.CreateErrorDetailRequest;
import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.service.errordetail.CreateErrorDetailService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for error details.
 * Provides endpoints for creating new error detail entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-detail" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-detail")
@RequiredArgsConstructor
public class CreateErrorDetailController extends BaseController {
    private final CreateErrorDetailService createErrorDetailService;

    /**
     * Creates a new error detail entry in the system.
     *
     * @param request the request containing error detail creation details
     * @return ResponseEntity containing the created error detail details
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<ErrorDetailResponse>> create(@RequestBody CreateErrorDetailRequest request) {
        return execute(createErrorDetailService.create(request), "201");
    }
}

