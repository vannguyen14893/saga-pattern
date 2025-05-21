package com.saga.admin.controller.group;

import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.service.group.GetListGroupService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling retrieval operations for groups.
 * Provides endpoints for fetching all group entries from the system.
 *
 * @RestController Indicates that this class serves REST endpoints
 * @RequestMapping Maps HTTP requests to "/group" path
 * @RequiredArgsConstructor Generates a constructor for final fields
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GetListGroupController extends BaseController {
    private final GetListGroupService getListGroupService;

    /**
     * Retrieves all group entries from the system.
     *
     * @return ResponseEntity containing a list of all groups
     */
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<GroupResponse>>> findAll() {
        return execute(getListGroupService.findAll(), "200");
    }

}
