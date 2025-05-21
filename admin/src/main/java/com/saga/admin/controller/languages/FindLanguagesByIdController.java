package com.saga.admin.controller.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.service.languages.FindLanguagesByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for languages by ID.
 * Provides endpoints for fetching specific language entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class FindLanguagesByIdController extends BaseController {
    private final FindLanguagesByIdService findLanguagesByIdService;

    /**
     * Retrieves a language entry by its ID.
     *
     * @param id the ID of the language to retrieve
     * @return ResponseEntity containing the found language details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<LanguagesResponse>> findById(@PathVariable Long id) {
        return execute(findLanguagesByIdService.findById(id), "200");
    }
}
