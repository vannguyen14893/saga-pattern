package com.saga.admin.controller.errormessage;

import com.saga.admin.dto.request.errormessage.UpdateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.UpdateErrorMessageService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for error messages.
 * Provides endpoints for updating existing error message entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-message" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-message")
@RequiredArgsConstructor
public class UpdateErrorMessageController extends BaseController {
    private final UpdateErrorMessageService updateErrorMessageService;

    /**
     * Updates an existing error message entry in the system.
     *
     * @param request the request containing updated error message details
     * @return ResponseEntity containing the updated error message details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<ErrorMessageResponse>> update(@RequestBody UpdateErrorMessageRequest request) {
        return execute(updateErrorMessageService.update(request), "200");
    }
}

