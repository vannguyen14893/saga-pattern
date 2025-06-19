package com.saga.admin.controller.errortype;

import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.service.errortype.GetListErrorTypeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for error types.
 * Provides endpoints for fetching all error type entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-type" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-type")
@RequiredArgsConstructor
public class GetListErrorTypeController extends BaseController {
    private final GetListErrorTypeService getListErrorTypeService;

    /**
     * Retrieves all error type entries from the system.
     *
     * @return ResponseEntity containing a list of all error types
     */
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<ErrorTypeResponse>>> findAll() {
        return execute(getListErrorTypeService.findAll(), "200");
    }
}

