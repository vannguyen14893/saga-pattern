package com.saga.order.entity;

import com.saga.database.config.AuditTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AuditTable {
    @Id
    private String id;
    private String code;
    private BigDecimal totalAmount = new BigDecimal(0);
    private LocalDateTime createdDate;
    private Long userIdOrder;
    private String status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderDetail> orderDetails;
}

