package com.saga.admin.controller.errorsystem;

import com.saga.admin.dto.request.errorsystem.UpdateErrorSystemRequest;
import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.service.errorsystem.UpdateErrorSystemService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for error systems.
 * Provides endpoints for updating existing error system entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-system" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-system")
@RequiredArgsConstructor
public class UpdateErrorSystemController extends BaseController {
    private final UpdateErrorSystemService updateErrorSystemService;

    /**
     * Updates an existing error system entry in the system.
     *
     * @param request the request containing error system update details
     * @return ResponseEntity containing the updated error system details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<ErrorSystemResponse>> update(@RequestBody UpdateErrorSystemRequest request) {
        return execute(updateErrorSystemService.update(request), "200");
    }
}

