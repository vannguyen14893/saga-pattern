package com.saga.admin.controller.errorsystem;

import com.saga.admin.dto.request.errorsystem.CreateErrorSystemRequest;
import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.service.errorsystem.CreateErrorSystemService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for error systems.
 * Provides endpoints for creating new error system entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-system" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-system")
@RequiredArgsConstructor
public class CreateErrorSystemController extends BaseController {
    private final CreateErrorSystemService createErrorSystemService;

    /**
     * Creates a new error system entry in the system.
     *
     * @param request the request containing error system creation details
     * @return ResponseEntity containing the created error system details
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<ErrorSystemResponse>> create(@RequestBody CreateErrorSystemRequest request) {
        return execute(createErrorSystemService.create(request), "201");
    }
}

