package com.saga.admin.controller.role;

import com.saga.admin.dto.request.role.CreateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.service.role.CreateRoleService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling role creation operations
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class CreateRoleController extends BaseController {
    private final CreateRoleService createRoleService;


    /**
     * Creates a new role based on the provided request
     *
     * @param createRoleRequest The request containing role details
     * @return ResponseEntity containing the created role response with status code 201
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<RoleResponse>> create(@RequestBody CreateRoleRequest createRoleRequest) {
        return execute(createRoleService.create(createRoleRequest), "201");
    }

}
