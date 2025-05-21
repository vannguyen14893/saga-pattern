package com.saga.admin.controller.role;

import com.saga.admin.service.role.DeleteRoleService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling role deletion operations
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class DeleteRoleController extends BaseController {
    private final DeleteRoleService deleteRoleService;

    /**
     * Deletes a role by its ID
     *
     * @param id The ID of the role to delete
     * @return ResponseEntity containing the ID of the deleted role
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deleteRoleService.delete(id), "200");
    }
}
