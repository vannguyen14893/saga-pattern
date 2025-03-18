package com.saga.dto.request;

public record CreateEmailNotifyRequest(String email, String subject, String content) {
}
