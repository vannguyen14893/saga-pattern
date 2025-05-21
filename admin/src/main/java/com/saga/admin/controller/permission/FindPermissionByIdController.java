package com.saga.admin.controller.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.service.permission.FindPermissionByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for handling permission retrieval operations by ID.
 * Extends BaseController to inherit common controller functionality.
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class FindPermissionByIdController extends BaseController {
    private final FindPermissionByIdService findPermissionByIdService;

    /**
     * Retrieves a permission by its unique identifier.
     *
     * @param id The unique identifier of the permission to retrieve
     * @return ResponseEntity containing the permission details wrapped in a ResponseSuccess object
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<PermissionResponse>> findById(@PathVariable Long id) {
        return execute(findPermissionByIdService.findById(id), "200");
    }

}
