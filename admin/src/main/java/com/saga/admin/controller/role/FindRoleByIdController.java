package com.saga.admin.controller.role;

import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.service.role.FindRoleByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling Role entity retrieval operations by ID
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class FindRoleByIdController extends BaseController {
    private final FindRoleByIdService findRoleByIdService;


    /**
     * Retrieves a role by its ID
     *
     * @param id The ID of the role to retrieve
     * @return ResponseEntity containing the found role wrapped in ResponseSuccess
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<RoleResponse>> findById(@PathVariable Long id) {
        return execute(findRoleByIdService.findById(id), "200");
    }
}
