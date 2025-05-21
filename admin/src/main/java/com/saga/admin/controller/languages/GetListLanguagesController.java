package com.saga.admin.controller.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.service.languages.GetListLanguagesService;
import com.saga.dto.response.PaginationResult;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for all languages.
 * Provides endpoints for fetching paginated list of language entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class GetListLanguagesController extends BaseController {
    private final GetListLanguagesService getListLanguagesService;

    /**
     * Retrieves all language entries from the system with pagination.
     *
     * @return ResponseEntity containing a paginated list of all languages
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<LanguagesResponse>>> findAll() {
        return execute(getListLanguagesService.findAll(), getListLanguagesService.findAll().size());
    }
}
