package com.saga.response.dto;

public record ResponseSuccess<T>(String id, int status, String message, T data) {

}
