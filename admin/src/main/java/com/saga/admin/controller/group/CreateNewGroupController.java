package com.saga.admin.controller.group;

import com.saga.admin.dto.request.group.CreateGroupRequest;
import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.service.group.CreateNewGroupService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling creation operations for groups.
 * Provides endpoints for creating new group entries in the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/group" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class CreateNewGroupController extends BaseController {
    private final CreateNewGroupService createNewGroupService;

    /**
     * Creates a new group entry in the system.
     *
     * @param createGroupRequest the request containing group creation details
     * @return ResponseEntity containing the created group details
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<GroupResponse>> create(@RequestBody CreateGroupRequest createGroupRequest) {
        return execute(createNewGroupService.create(createGroupRequest), "201");
    }

}
