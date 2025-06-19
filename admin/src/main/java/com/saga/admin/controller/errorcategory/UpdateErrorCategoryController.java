package com.saga.admin.controller.errorcategory;

import com.saga.admin.dto.request.errorcategory.UpdateErrorCategoryRequest;
import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.service.errorcategory.UpdateErrorCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for error categories.
 * Provides endpoints for updating existing error category entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/api/error-categories" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/api/error-categories")
@RequiredArgsConstructor
public class UpdateErrorCategoryController {
    private final UpdateErrorCategoryService updateService;

    /**
     * Update an existing ErrorCategory by id.
     */
    @PutMapping("/{id}")
    public ErrorCategoryResponse update(@PathVariable Long id, @RequestBody UpdateErrorCategoryRequest request) {
        return updateService.update(id, request);
    }
}

