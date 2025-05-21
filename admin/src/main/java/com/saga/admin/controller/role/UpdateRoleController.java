package com.saga.admin.controller.role;

import com.saga.admin.dto.request.role.UpdateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.service.role.UpdateRoleService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling role update operations
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class UpdateRoleController extends BaseController {
    private final UpdateRoleService updateRoleService;


    /**
     * Updates an existing role
     *
     * @param updateRoleRequest The request containing updated role details
     * @return ResponseEntity containing the updated role information
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<RoleResponse>> update(@RequestBody UpdateRoleRequest updateRoleRequest) {
        return execute(updateRoleService.update(updateRoleRequest), "201");
    }

}
