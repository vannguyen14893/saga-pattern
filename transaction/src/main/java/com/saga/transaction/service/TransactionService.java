package com.saga.transaction.service;

import com.saga.transaction.entity.Transaction;
import com.saga.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }

    public Transaction findById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAllByUserId(Long userId) {
        return transactionRepository.findAllByUserId(userId).collect(Collectors.toList());
    }

    public Transaction create(Transaction transaction) {
        transaction.setId(UUID.randomUUID().toString());
        transaction.setCreatedDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

}
