package com.saga.admin.controller.errortype;

import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.service.errortype.FindErrorTypeByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for error types by ID.
 * Provides endpoints for fetching specific error type entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-type" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-type")
@RequiredArgsConstructor
public class FindErrorTypeByIdController extends BaseController {
    private final FindErrorTypeByIdService findErrorTypeByIdService;

    /**
     * Retrieves an error type entry by its ID.
     *
     * @param id the ID of the error type to retrieve
     * @return ResponseEntity containing the found error type details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<ErrorTypeResponse>> findById(@PathVariable Long id) {
        return execute(findErrorTypeByIdService.findById(id), "200");
    }
}

