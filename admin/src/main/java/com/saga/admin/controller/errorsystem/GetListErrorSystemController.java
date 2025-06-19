package com.saga.admin.controller.errorsystem;

import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.service.errorsystem.GetListErrorSystemService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for error systems.
 * Provides endpoints for fetching all error system entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-system" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-system")
@RequiredArgsConstructor
public class GetListErrorSystemController extends BaseController {
    private final GetListErrorSystemService getListErrorSystemService;

    /**
     * Retrieves all error system entries in the system.
     *
     * @return ResponseEntity containing the list of error system details
     */
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<ErrorSystemResponse>>> findAll() {
        return execute(getListErrorSystemService.findAll(), "200");
    }
}

