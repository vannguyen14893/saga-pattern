package com.saga.admin.controller.languages;

import com.saga.admin.dto.request.languages.CreateLanguagesRequest;
import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.service.languages.CreateLanguagesService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for languages.
 * Provides endpoints for creating new language entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class CreateNewLanguagesController extends BaseController {
    private final CreateLanguagesService createLanguagesService;


    @PostMapping
    public ResponseEntity<ResponseSuccess<LanguagesResponse>> create(@RequestBody @Valid CreateLanguagesRequest createLanguagesRequest) {
        return execute(createLanguagesService.create(createLanguagesRequest), "201");
    }


}
