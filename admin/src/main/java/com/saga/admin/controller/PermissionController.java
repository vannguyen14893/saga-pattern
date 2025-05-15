package com.saga.admin.controller;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.CreatePermissionRequest;
import com.saga.admin.dto.response.permission.UpdatePermissionRequest;
import com.saga.admin.service.PermissionService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController extends BaseController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<PermissionResponse>>> findAll() {
        return execute(permissionService.findAll(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<PermissionResponse>> findById(@PathVariable Long id) {
        return execute(permissionService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<PermissionResponse>> create(@RequestBody CreatePermissionRequest createPermissionRequest) {
        return execute(permissionService.create(createPermissionRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<PermissionResponse>> update(@RequestBody UpdatePermissionRequest updatePermissionRequest) {
        return execute(permissionService.update(updatePermissionRequest), 201);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(permissionService.delete(id), 200);
    }
}
