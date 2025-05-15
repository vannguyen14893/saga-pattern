package com.example.authorzation.repository;

import com.example.authorzation.entity.DeviceCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceCodeRepository extends JpaRepository<DeviceCode, String> {
    DeviceCode findByDeviceCodeAndUserCode(String deviceCode, String userCode);
}
