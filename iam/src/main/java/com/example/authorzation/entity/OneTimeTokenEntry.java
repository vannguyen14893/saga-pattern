package com.example.authorzation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneTimeTokenEntry {
    @Id
    private String id;
    private String username;
    private String token;
    private LocalDateTime createdAt;
}
