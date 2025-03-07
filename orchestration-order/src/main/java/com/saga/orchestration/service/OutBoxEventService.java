package com.saga.orchestration.service;

import com.saga.orchestration.entity.OutboxEvent;
import com.saga.orchestration.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutBoxEventService {
    private final OutboxEventRepository outboxEventRepository;

    public void create(OutboxEvent outboxEvent) {
        outboxEventRepository.save(outboxEvent);
    }
}
