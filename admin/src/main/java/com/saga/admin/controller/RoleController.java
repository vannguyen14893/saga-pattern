package com.saga.admin.controller;

import com.saga.admin.dto.request.role.CreateRoleRequest;
import com.saga.admin.dto.request.role.UpdateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.service.RoleService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController extends BaseController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<RoleResponse>>> findAll() {
        return execute(roleService.findAll(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<RoleResponse>> findById(@PathVariable Long id) {
        return execute(roleService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<RoleResponse>> create(@RequestBody CreateRoleRequest createRoleRequest) {
        return execute(roleService.create(createRoleRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<RoleResponse>> update(@RequestBody UpdateRoleRequest updateRoleRequest) {
        return execute(roleService.update(updateRoleRequest), 201);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(roleService.delete(id), 200);
    }
}
