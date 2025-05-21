package com.saga.admin.service.outboxevent;


import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing outbox events
 */
@Service
@RequiredArgsConstructor
public class OutBoxEventService {
    private final OutboxEventRepository outboxEventRepository;

    /**
     * Creates a single outbox event
     *
     * @param outboxEvent The outbox event to create
     */
    public void create(OutboxEvent outboxEvent) {
        outboxEventRepository.save(outboxEvent);
    }

    /**
     * Creates multiple outbox events
     *
     * @param outboxEvents List of outbox events to create
     */
    public void createMulti(List<OutboxEvent> outboxEvents) {
        outboxEventRepository.saveAll(outboxEvents);
    }
}