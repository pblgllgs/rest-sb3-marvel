package com.pblgllgs.restsb3marvel.exception;

public record ApiErrorDto(
        String message,
        String messageBackend,
        String method,
        String url
) {
}
