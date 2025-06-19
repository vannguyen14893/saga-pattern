package com.saga.admin.controller.errormessage;

import com.saga.admin.dto.request.errormessage.CreateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.CreateErrorMessageService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for error messages.
 * Provides endpoints for creating new error message entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-message" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-message")
@RequiredArgsConstructor
public class CreateErrorMessageController extends BaseController {
    private final CreateErrorMessageService createErrorMessageService;

    /**
     * Creates a new error message entry in the system.
     *
     * @param request the request containing error message creation details
     * @return ResponseEntity containing the created error message details
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<ErrorMessageResponse>> create(@RequestBody CreateErrorMessageRequest request) {
        return execute(createErrorMessageService.create(request), "201");
    }
}

