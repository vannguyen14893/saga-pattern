package com.saga.email.adapter.consumer;

import com.google.gson.Gson;
import com.saga.dto.request.EmailNotify;
import com.saga.email.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final SendEmailService sendEmailService;


    @KafkaListener(
            topics = "email",
            groupId = "${kafka.group-id}")
    public void listenNotify(List<String> emailNotifies) {
        for (String emailNotify : emailNotifies) {
            EmailNotify request = new Gson().fromJson(emailNotify, EmailNotify.class);
            sendEmailService.execute(request);
        }
    }
}
