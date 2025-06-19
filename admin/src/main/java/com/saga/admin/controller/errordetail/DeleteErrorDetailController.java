package com.saga.admin.controller.errordetail;

import com.saga.admin.service.errordetail.DeleteErrorDetailService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for error details.
 * Provides endpoints for removing error detail entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/error-detail" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/error-detail")
@RequiredArgsConstructor
public class DeleteErrorDetailController extends BaseController {
    private final DeleteErrorDetailService deleteErrorDetailService;

    /**
     * Deletes an error detail entry by its ID.
     *
     * @param id the ID of the error detail to delete
     * @return ResponseEntity containing the ID of the deleted error detail
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteErrorDetailService.delete(id), "200");
    }
}

