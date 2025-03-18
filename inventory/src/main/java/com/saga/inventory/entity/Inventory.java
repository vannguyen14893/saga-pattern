package com.saga.inventory.entity;

import com.saga.database.config.AuditTable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends AuditTable {
    @Id
    private Long productId;
    private int quantity;
//    private int minimumStockLevel;
//    private int maximumStockLevel;
}

