package com.springboot.springboot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_EXISTED("1001", "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("1002", "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("1003", "FORBIDDEN", HttpStatus.FORBIDDEN),
    ROLE_NOT_EXISTED("1004", "Role does not exist", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND("1005", "Role not found", HttpStatus.NOT_FOUND);
    private final String code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(String code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}

