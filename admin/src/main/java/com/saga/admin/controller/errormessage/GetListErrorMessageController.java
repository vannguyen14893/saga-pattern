package com.saga.admin.controller.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.service.errormessage.GetListErrorMessageService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for error messages.
 * Provides endpoints for fetching all error message entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-message" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-message")
@RequiredArgsConstructor
public class GetListErrorMessageController extends BaseController {
    private final GetListErrorMessageService getListErrorMessageService;

    /**
     * Retrieves all error message entries from the system.
     *
     * @return ResponseEntity containing a list of all error messages
     */
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<ErrorMessageResponse>>> findAll() {
        return execute(getListErrorMessageService.findAll(), "200");
    }

}