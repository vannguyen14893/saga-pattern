package com.saga.admin.controller.errorcategory;

import com.saga.admin.service.errorcategory.DeleteErrorCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for error categories.
 * Provides endpoints for removing error category entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/api/error-categories" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/api/error-categories")
@RequiredArgsConstructor
public class DeleteErrorCategoryController {
    private final DeleteErrorCategoryService deleteService;

    /**
     * Delete an ErrorCategory by id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteService.delete(id);
    }
}

