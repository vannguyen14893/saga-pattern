package com.saga.admin.controller.role;

import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.service.role.GetListRoleService;
import com.saga.dto.response.PaginationResult;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling role listing operations
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class GetListRoleController extends BaseController {
    private final GetListRoleService getListRoleService;

    /**
     * Retrieves all roles with pagination
     *
     * @return ResponseEntity containing paginated list of roles
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<RoleResponse>>> findAll() {
        return execute(getListRoleService.findAll(), getListRoleService.findAll().size());
    }

}
