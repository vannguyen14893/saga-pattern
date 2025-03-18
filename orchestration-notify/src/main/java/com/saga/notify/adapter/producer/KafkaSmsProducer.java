package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSmsProducer {

    private final KafkaTemplate<String, String> smsKafkaTemplate;

    @Async
    public void sendSms(String smsNotify) {
        smsKafkaTemplate.send("sms", smsNotify);
    }
}
