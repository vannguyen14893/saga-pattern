package com.saga.admin.controller.group;

import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.service.group.FindGroupByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling retrieval operations for groups by ID.
 * Provides endpoints for fetching specific group entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/group" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class FindGroupByIdController extends BaseController {
    private final FindGroupByIdService findGroupByIdService;

    /**
     * Retrieves a group entry by its ID.
     *
     * @param id the ID of the group to retrieve
     * @return ResponseEntity containing the found group details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<GroupResponse>> findById(@PathVariable Long id) {
        return execute(findGroupByIdService.findById(id), "200");
    }

}
