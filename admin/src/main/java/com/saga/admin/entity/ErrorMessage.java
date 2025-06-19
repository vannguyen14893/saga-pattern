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
@Table(name = "error_messages", uniqueConstraints = @UniqueConstraint(columnNames = {"error_type_id", "language_code"}))
public class ErrorMessage extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "error_type_id", nullable = false)
    private ErrorType errorType;

    private String languageCode = "en";

    @Column(columnDefinition = "TEXT")
    private String text;

}