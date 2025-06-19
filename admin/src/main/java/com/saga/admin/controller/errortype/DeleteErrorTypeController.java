package com.saga.admin.controller.errortype;

import com.saga.admin.service.errortype.DeleteErrorTypeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for error types.
 * Provides endpoints for removing error type entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-type" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-type")
@RequiredArgsConstructor
public class DeleteErrorTypeController extends BaseController {
    private final DeleteErrorTypeService deleteErrorTypeService;

    /**
     * Deletes an error type entry by its ID.
     *
     * @param id the ID of the error type to delete
     * @return ResponseEntity containing the ID of the deleted error type
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteErrorTypeService.delete(id), "200");
    }
}

