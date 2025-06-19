package com.saga.admin.controller.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.FindErrorMessageByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for error messages by ID.
 * Provides endpoints for fetching specific error message entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-message" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-message")
@RequiredArgsConstructor
public class FindErrorMessageByIdController extends BaseController {
    private final FindErrorMessageByIdService findErrorMessageByIdService;

    /**
     * Retrieves an error message entry by its ID.
     *
     * @param id the ID of the error message to retrieve
     * @return ResponseEntity containing the found error message details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<ErrorMessageResponse>> findById(@PathVariable Long id) {
        return execute(findErrorMessageByIdService.findById(id), "200");
    }
}

