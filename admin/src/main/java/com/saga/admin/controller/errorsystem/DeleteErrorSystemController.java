package com.saga.admin.controller.errorsystem;

import com.saga.admin.service.errorsystem.DeleteErrorSystemService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for error systems.
 * Provides endpoints for removing error system entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-system" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-system")
@RequiredArgsConstructor
public class DeleteErrorSystemController extends BaseController {
    private final DeleteErrorSystemService deleteErrorSystemService;

    /**
     * Deletes an error system entry by its ID.
     *
     * @param id the ID of the error system to delete
     * @return ResponseEntity containing the ID of the deleted error system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteErrorSystemService.deleteById(id), "200");
    }
}

