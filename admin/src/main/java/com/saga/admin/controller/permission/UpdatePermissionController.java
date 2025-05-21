package com.saga.admin.controller.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.UpdatePermissionRequest;
import com.saga.admin.service.permission.UpdatePermissionService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling permission update operations.
 * Extends BaseController to inherit common controller functionality.
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class UpdatePermissionController extends BaseController {
    private final UpdatePermissionService updatePermissionService;


    /**
     * Updates an existing permission with the provided details.
     *
     * @param updatePermissionRequest The request containing updated permission details
     * @return ResponseEntity containing the updated permission details wrapped in a ResponseSuccess object
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<PermissionResponse>> update(@RequestBody UpdatePermissionRequest updatePermissionRequest) {
        return execute(updatePermissionService.update(updatePermissionRequest), "201");
    }
}
