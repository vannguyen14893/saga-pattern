package com.saga.admin.controller.languages;

import com.saga.admin.dto.request.languages.UpdateLanguagesRequest;
import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.service.languages.UpdateLanguagesService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for languages.
 * Provides endpoints for modifying existing language entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class UpdateLanguagesController extends BaseController {
    private final UpdateLanguagesService updateLanguagesService;


    /**
     * Updates an existing language entry in the system.
     *
     * @param updateLanguagesRequest the request containing updated language details
     * @return ResponseEntity containing the updated language details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<LanguagesResponse>> update(@RequestBody @Valid UpdateLanguagesRequest updateLanguagesRequest) {
        return execute(updateLanguagesService.update(updateLanguagesRequest), "200");
    }

}
