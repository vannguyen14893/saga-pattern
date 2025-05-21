package com.saga.admin.controller.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.service.languages.GetLanguagesActiveService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for active languages.
 * Provides endpoints for fetching all active language entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class GetLanguagesActiveController extends BaseController {
    private final GetLanguagesActiveService getLanguagesActiveService;


    /**
     * Retrieves all active language entries from the system.
     *
     * @return ResponseEntity containing a list of all active languages
     */
    @GetMapping("/active")
    public ResponseEntity<ResponseSuccess<List<LanguagesResponse>>> findAllByActiveIsTrue() {
        return execute(getLanguagesActiveService.findAllByActiveIsTrue(), "200");
    }

}
