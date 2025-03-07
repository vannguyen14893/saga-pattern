package com.saga.transaction.repository;

import com.saga.transaction.entity.Transaction;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.stream.Stream;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    Stream<Transaction> findAllByUserId(Long userId);
}
