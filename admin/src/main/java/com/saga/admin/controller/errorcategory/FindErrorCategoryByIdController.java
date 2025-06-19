package com.saga.admin.controller.errorcategory;

import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.service.errorcategory.FindErrorCategoryByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for error categories by ID.
 * Provides endpoints for fetching specific error category entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/api/error-categories" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/api/error-categories")
@RequiredArgsConstructor
public class FindErrorCategoryByIdController {
    private final FindErrorCategoryByIdService findErrorCategoryByIdService;

    /**
     * Retrieves an error category entry by its ID.
     *
     * @param id the ID of the error category to retrieve
     * @return ErrorCategoryResponse containing the found error category details
     */
    @GetMapping("/{id}")
    public ErrorCategoryResponse findById(@PathVariable Long id) {
        return findErrorCategoryByIdService.findById(id);
    }
}


