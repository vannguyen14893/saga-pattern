package com.saga.admin.dto.request.role;

import java.util.List;

public record CreateRoleRequest(String name, String description, List<Long> groupIds) {
}
