package com.saga.admin.controller;

import com.saga.admin.dto.request.group.CreateGroupRequest;
import com.saga.admin.dto.request.group.UpdateGroupRequest;
import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.service.GroupService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController extends BaseController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<GroupResponse>>> findAll() {
        return execute(groupService.findAll(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<GroupResponse>> findById(@PathVariable Long id) {
        return execute(groupService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<GroupResponse>> create(@RequestBody CreateGroupRequest createGroupRequest) {
        return execute(groupService.create(createGroupRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<GroupResponse>> update(@RequestBody UpdateGroupRequest updateGroupRequest) {
        return execute(groupService.update(updateGroupRequest), 201);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> delete(@PathVariable Long id) {
        return execute(groupService.delete(id), 200);
    }
}
