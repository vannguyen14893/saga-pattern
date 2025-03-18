package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaPushProducer {


    private final KafkaTemplate<String, String> pushKafkaTemplate;

    @Async
    public void pushNotify(String pushNotify) {
        pushKafkaTemplate.send("push", pushNotify);
    }
}
