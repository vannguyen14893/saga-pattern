package com.saga.admin.controller.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.CreatePermissionRequest;
import com.saga.admin.service.permission.CreatePermissionService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling permission creation requests.
 * Extends BaseController to inherit common controller functionality.
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class CreatePermissionController extends BaseController {
    private final CreatePermissionService createPermissionService;

    /**
     * Creates a new permission based on the provided request.
     *
     * @param createPermissionRequest The request containing permission details to create
     * @return ResponseEntity containing the created permission response with status code 201
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<PermissionResponse>> create(@RequestBody CreatePermissionRequest createPermissionRequest) {
        return execute(createPermissionService.create(createPermissionRequest), "201");
    }

}
