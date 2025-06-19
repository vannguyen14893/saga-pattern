package com.saga.admin.controller.errordetail;

import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.service.errordetail.GetListErrorDetailService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for error details.
 * Provides endpoints for fetching all error detail entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-detail" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-detail")
@RequiredArgsConstructor
public class GetListErrorDetailController extends BaseController {
    private final GetListErrorDetailService getListErrorDetailService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<ErrorDetailResponse>>> findAll() {
        return execute(getListErrorDetailService.findAll(), "200");
    }
}
