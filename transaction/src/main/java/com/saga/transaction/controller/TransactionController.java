package com.saga.transaction.controller;

import com.saga.transaction.entity.Transaction;
import com.saga.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> list() {
        return new ResponseEntity<>(transactionService.list(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable String id) {
        return new ResponseEntity<>(transactionService.findById(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Transaction>> findAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(transactionService.findAllByUserId(userId), HttpStatusCode.valueOf(200));
    }
}
