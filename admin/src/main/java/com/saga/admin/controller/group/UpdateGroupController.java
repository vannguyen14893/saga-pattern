package com.saga.admin.controller.group;

import com.saga.admin.dto.request.group.UpdateGroupRequest;
import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.service.group.UpdateGroupService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling update operations for groups.
 * Provides endpoints for modifying existing group entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/group" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class UpdateGroupController extends BaseController {
    private final UpdateGroupService updateGroupService;

    /**
     * Updates an existing group entry in the system.
     *
     * @param updateGroupRequest the request containing updated group details
     * @return ResponseEntity containing the updated group details
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<GroupResponse>> update(@RequestBody UpdateGroupRequest updateGroupRequest) {
        return execute(updateGroupService.update(updateGroupRequest), "201");
    }

}
