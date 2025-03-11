package com.saga.response.dto;

public record ResponseError<T>(String id, int status, T message) {

}
