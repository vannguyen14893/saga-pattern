package com.saga.admin.controller.errordetail;

import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.service.errordetail.FindErrorDetailByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for error details by ID.
 * Provides endpoints for fetching specific error detail entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-detail" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-detail")
@RequiredArgsConstructor
public class FindErrorDetailByIdController extends BaseController {
    private final FindErrorDetailByIdService findErrorDetailByIdService;

    /**
     * Retrieves an error detail entry by its ID.
     *
     * @param id the ID of the error detail to retrieve
     * @return ResponseEntity containing the found error detail details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<ErrorDetailResponse>> findById(@PathVariable Long id) {
        return execute(findErrorDetailByIdService.findById(id), "200");
    }
}

