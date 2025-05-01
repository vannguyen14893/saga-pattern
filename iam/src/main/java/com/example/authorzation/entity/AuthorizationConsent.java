package com.example.authorzation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "oauth2_authorization_consent")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
@Data
public class AuthorizationConsent {
    @Id
    private String registeredClientId;
    @Id
    private String principalName;
    @Column(length = 1000)
    private String authorities;

    @Data
    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;
    }
}