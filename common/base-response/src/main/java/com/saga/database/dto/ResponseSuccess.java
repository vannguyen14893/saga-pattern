package com.saga.database.dto;

public record ResponseSuccess<T>(String id, int status, String message, T data) {

}
