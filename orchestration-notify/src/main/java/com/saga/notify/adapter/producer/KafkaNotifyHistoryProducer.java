package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotifyHistoryProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendNotifyHistory(String notifyHistory) {
        kafkaTemplate.send("outbox-event", notifyHistory);
    }
}
