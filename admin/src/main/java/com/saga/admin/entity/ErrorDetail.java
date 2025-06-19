package com.saga.admin.entity;

import com.saga.database.config.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_details", uniqueConstraints = @UniqueConstraint(columnNames = {"error_type_id", "detail_key"}))
public class ErrorDetail extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "error_type_id", nullable = false)
    private ErrorType errorType;

    @Column(name = "detail_key")
    private String key;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_required", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean required = false;

    @Column(columnDefinition = "TEXT")
    private String exampleValue;
}
