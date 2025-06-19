package com.saga.admin.controller.errorcategory;

import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.service.errorcategory.GetListErrorCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for error categories.
 * Provides endpoints for fetching all error category entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/api/error-categories" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/api/error-categories")
@RequiredArgsConstructor
public class GetListErrorCategoryController {
    private final GetListErrorCategoryService getListService;

    /**
     * Get list of all ErrorCategories.
     */
    @GetMapping
    public List<ErrorCategoryResponse> getAll() {
        return getListService.findAll();
    }
}

