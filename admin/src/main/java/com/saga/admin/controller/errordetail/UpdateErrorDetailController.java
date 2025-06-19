package com.saga.admin.controller.errordetail;

import com.saga.admin.dto.request.errordetail.UpdateErrorDetailRequest;
import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.service.errordetail.UpdateErrorDetailService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for error details.
 * Provides endpoints for updating existing error detail entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-detail" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-detail")
@RequiredArgsConstructor
public class UpdateErrorDetailController extends BaseController {
    private final UpdateErrorDetailService updateErrorDetailService;

    /**
     * Updates an existing error detail entry in the system.
     *
     * @param request the request containing error detail update details
     * @return ResponseEntity containing the updated error detail details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<ErrorDetailResponse>> update(@RequestBody UpdateErrorDetailRequest request) {
        return execute(updateErrorDetailService.update(request), "200");
    }
}

