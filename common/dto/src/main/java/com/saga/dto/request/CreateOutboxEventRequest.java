package com.saga.dto.request;

public record CreateOutboxEventRequest (String id,String aggregateType,String aggregateId,String eventType,String payload) {

}
