package com.saga.response.dto;

public record PaginationResult<T>(T data, long total) {

}
