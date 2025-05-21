package com.saga.admin.controller.languages;

import com.saga.admin.service.languages.DeleteLanguagesService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for languages.
 * Provides endpoints for removing language entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/languages" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class DeleteLanguagesController extends BaseController {
    private final DeleteLanguagesService deleteLanguagesService;


    /**
     * Deletes a language entry by its ID.
     *
     * @param id the ID of the language to delete
     * @return ResponseEntity containing the ID of the deleted language
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> deleteById(@PathVariable Long id) {
        return execute(deleteLanguagesService.delete(id), "200");
    }
}
