package com.saga.admin.controller.errortype;

import com.saga.admin.dto.request.errortype.CreateErrorTypeRequest;
import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.service.errortype.CreateErrorTypeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for error types.
 * Provides endpoints for creating new error type entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-type" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-type")
@RequiredArgsConstructor
public class CreateErrorTypeController extends BaseController {
    private final CreateErrorTypeService createErrorTypeService;

    /**
     * Creates a new error type entry in the system.
     *
     * @param request the request containing error type creation details
     * @return ResponseEntity containing the created error type details
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<ErrorTypeResponse>> create(@RequestBody CreateErrorTypeRequest request) {
        return execute(createErrorTypeService.create(request), "201");
    }
}

