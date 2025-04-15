package com.springboot.springboot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_EXISTED("1001", "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("1002", "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("1003", "FORBIDDEN", HttpStatus.FORBIDDEN);

    private final String code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(String code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}

