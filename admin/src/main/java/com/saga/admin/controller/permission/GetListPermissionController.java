package com.saga.admin.controller.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.service.permission.GetListPermissionService;
import com.saga.dto.response.PaginationResult;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling permission listing operations.
 * Extends BaseController to inherit common controller functionality.
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class GetListPermissionController extends BaseController {
    private final GetListPermissionService getListPermissionService;

    /**
     * Retrieves all permissions with pagination.
     *
     * @return ResponseEntity containing a paginated list of permissions
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<PermissionResponse>>> findAll() {
        return execute(getListPermissionService.findAll(), getListPermissionService.findAll().size());
    }
}
