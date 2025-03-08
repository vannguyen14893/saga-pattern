package com.saga.database.dto;

public record ResponseError<T>(String id, int status, T message) {

}
