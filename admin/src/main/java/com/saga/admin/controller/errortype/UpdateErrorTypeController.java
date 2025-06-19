package com.saga.admin.controller.errortype;

import com.saga.admin.dto.request.errortype.UpdateErrorTypeRequest;
import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.service.errortype.UpdateErrorTypeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for error types.
 * Provides endpoints for updating existing error type entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-type" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-type")
@RequiredArgsConstructor
public class UpdateErrorTypeController extends BaseController {
    private final UpdateErrorTypeService updateErrorTypeService;

    /**
     * Updates an existing error type entry in the system.
     *
     * @param request the request containing updated error type details
     * @return ResponseEntity containing the updated error type details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<ErrorTypeResponse>> update(@RequestBody UpdateErrorTypeRequest request) {
        return execute(updateErrorTypeService.update(request), "200");
    }
}

