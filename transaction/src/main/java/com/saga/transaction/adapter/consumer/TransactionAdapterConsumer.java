package com.saga.transaction.adapter.consumer;

import com.google.gson.Gson;
import com.saga.dto.request.TransactionAdapterRequest;
import com.saga.transaction.entity.Transaction;
import com.saga.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionAdapterConsumer {
    private final TransactionService transactionService;

    @KafkaListener(topics = "transaction", groupId = "${kafka.group-id}")
    public void receive(String payload) {
        TransactionAdapterRequest transactionAdapterRequest = new Gson().fromJson(payload, TransactionAdapterRequest.class);
        Transaction transaction = new Transaction();
        transaction.setCode(transactionAdapterRequest.code());
        transaction.setType(transactionAdapterRequest.type());
        transaction.setStatus(transactionAdapterRequest.status());
        transaction.setUserId(transactionAdapterRequest.userId());
        transaction.setAmount(transactionAdapterRequest.amount());
        transactionService.create(transaction);
    }
}

