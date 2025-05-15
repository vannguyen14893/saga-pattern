package com.saga.admin.dto.request.role;

import java.util.List;

public record UpdateRoleRequest(Long id, String name, String description, List<Long> groupIds) {
}
