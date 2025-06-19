package com.saga.admin.controller.errormessage;

import com.saga.admin.service.errormessage.DeleteErrorMessageService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for error messages.
 * Provides endpoints for removing error message entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-message" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-message")
@RequiredArgsConstructor
public class DeleteErrorMessageController extends BaseController {
    private final DeleteErrorMessageService deleteErrorMessageService;

    /**
     * Deletes an error message entry by its ID.
     *
     * @param id the ID of the error message to delete
     * @return ResponseEntity containing the ID of the deleted error message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteErrorMessageService.delete(id), "200");
    }
}

