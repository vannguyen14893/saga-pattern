package com.saga.admin.service.role;

import com.saga.admin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for deleting Role entities
 */
@Service
@RequiredArgsConstructor
public class DeleteRoleService {
    private final RoleRepository roleRepository;

    /**
     * Deletes a role by its ID
     *
     * @param id The ID of the role to delete
     * @return The ID of the deleted role
     */
    public Long delete(Long id) {
        roleRepository.deleteById(id);
        return id;
    }

}
