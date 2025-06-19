package com.saga.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_types", uniqueConstraints = @UniqueConstraint(columnNames = {"category_id", "name"}))
public class ErrorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ErrorCategory category;
    private String name;
    private String baseCode;
    private Integer httpStatus;
    @Column(name = "is_deprecated", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deprecated = false;

    @OneToMany(mappedBy = "errorType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorMessage> messages = new ArrayList<>();

    @OneToMany(mappedBy = "errorType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorDetail> details = new ArrayList<>();

}