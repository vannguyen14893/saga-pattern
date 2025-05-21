package com.example.authorzation.repository;

import com.example.authorzation.entity.DeviceCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing DeviceCode entities.
 * Provides CRUD operations and custom queries for device code authentication.
 */
public interface DeviceCodeRepository extends JpaRepository<DeviceCode, String> {
    /**
     * Finds a DeviceCode entity by matching both device code and user code.
     *
     * @param deviceCode the device code to search for
     * @param userCode   the user code to search for
     * @return the matching DeviceCode entity or null if not found
     */
    DeviceCode findByDeviceCodeAndUserCode(String deviceCode, String userCode);
}
