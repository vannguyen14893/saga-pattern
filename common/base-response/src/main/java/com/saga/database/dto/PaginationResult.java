package com.saga.database.dto;

public record PaginationResult<T>(T data, long total) {

}
