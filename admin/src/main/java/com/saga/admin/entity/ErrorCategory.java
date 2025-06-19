package com.saga.admin.entity;

import com.saga.database.config.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_categories", uniqueConstraints = @UniqueConstraint(columnNames = {"system_id", "name"}))
public class ErrorCategory extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private ErrorSystem system;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorType> errorTypes;
}