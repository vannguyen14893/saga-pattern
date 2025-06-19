package com.saga.admin.controller.errorsystem;

import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.service.errorsystem.FindErrorSystemByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for error systems by ID.
 * Provides endpoints for fetching specific error system entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-system" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-system")
@RequiredArgsConstructor
public class FindErrorSystemByIdController extends BaseController {
    private final FindErrorSystemByIdService findErrorSystemByIdService;

    /**
     * Retrieves an error system entry by its ID.
     *
     * @param id the ID of the error system to retrieve
     * @return ResponseEntity containing the found error system details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<ErrorSystemResponse>> findById(@PathVariable Long id) {
        return execute(findErrorSystemByIdService.findById(id), "200");
    }
}

