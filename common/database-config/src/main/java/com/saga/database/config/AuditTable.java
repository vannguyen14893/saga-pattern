package com.saga.database.config;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base abstract class that provides auditing functionality for entity classes.
 * Includes created/updated timestamps and user information tracking.
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditTable implements Serializable {
    private static final long serialVersionUID = 887258922870392611L;
    /**
     * ID of the user who created the entity
     */
    @CreatedBy
    @Column(name = "created_by_id")
    private Long createdById;
    /**
     * ID of the user who last modified the entity
     */
    @LastModifiedBy
    @Column(name = " update_by_id")
    private Long updateById;
    /**
     * Timestamp when the entity was created
     */
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    /**
     * Timestamp when the entity was last updated
     */
    @Column(name = "updated_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;
}
