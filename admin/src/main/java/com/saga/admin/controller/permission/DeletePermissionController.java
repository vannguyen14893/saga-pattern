package com.saga.admin.controller.permission;

import com.saga.admin.service.permission.DeletePermissionService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling permission deletion requests.
 * Extends BaseController to inherit common controller functionality.
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class DeletePermissionController extends BaseController {
    private final DeletePermissionService deletePermissionService;


    /**
     * Deletes a permission with the specified ID.
     *
     * @param id The ID of the permission to delete
     * @return ResponseEntity containing the deleted permission ID with status code 200
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(deletePermissionService.delete(id), "200");
    }
}
