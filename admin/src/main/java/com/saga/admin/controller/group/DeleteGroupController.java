package com.saga.admin.controller.group;

import com.saga.admin.service.group.DeleteGroupService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling deletion operations for groups.
 * Provides endpoints for removing group entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/group" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class DeleteGroupController extends BaseController {
    private final DeleteGroupService deleteGroupService;

    /**
     * Deletes a group entry by its ID.
     *
     * @param id the ID of the group to delete
     * @return ResponseEntity containing the ID of the deleted group
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteGroupService.delete(id), "200");
    }
}
