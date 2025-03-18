package com.saga.admin.service;


import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutBoxEventService {
    private final OutboxEventRepository outboxEventRepository;

    public void create(OutboxEvent outboxEvent) {
        outboxEventRepository.save(outboxEvent);
    }

    public void createMulti(List<OutboxEvent> outboxEvents) {
        outboxEventRepository.saveAll(outboxEvents);
    }
}
