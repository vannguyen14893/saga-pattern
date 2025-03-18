package com.saga.notify.adapter.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEmailProducer {


    private final KafkaTemplate<String, String> emailKafkaTemplate;

    @Async
    public void sendEmail(String emailNotify) {
        emailKafkaTemplate.send("email", emailNotify);
    }
}
