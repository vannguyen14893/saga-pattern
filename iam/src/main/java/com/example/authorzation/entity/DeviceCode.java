package com.example.authorzation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth2_device_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCode {
    @Id
    private String deviceCode;
    @Column(name = "user_code")
    private String userCode;
    private String clientId;
    private String scopes;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String verificationUri;
    private Integer verificationUriComplete;
    //private Integer interval;
}
