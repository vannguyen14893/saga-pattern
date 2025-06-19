package com.saga.admin.controller.errorcategory;

import com.saga.admin.dto.request.errorcategory.CreateErrorCategoryRequest;
import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.service.errorcategory.CreateErrorCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for error categories.
 * Provides endpoints for creating new error category entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/api/error-categories" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/api/error-categories")
@RequiredArgsConstructor
public class CreateErrorCategoryController {
    private final CreateErrorCategoryService createService;

    /**
     * Create a new ErrorCategory.
     */
    @PostMapping
    public ErrorCategoryResponse create(@RequestBody CreateErrorCategoryRequest request) {
        return createService.create(request);
    }
}

