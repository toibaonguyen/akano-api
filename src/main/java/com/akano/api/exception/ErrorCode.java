package com.akano.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import jakarta.validation.constraints.NotNull;

@Getter
public enum ErrorCode {

    // ---- Authentication & Authorization ----
    UNAUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "You do not have permission", HttpStatus.FORBIDDEN),

    // ---- User ----
    USER_NOT_REGISTERED(2001, "User not registered", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS(2002, "User already exists", HttpStatus.CONFLICT),

    // ---- Validation ----
    INVALID_FIELD(3001, "Validation failed", HttpStatus.BAD_REQUEST),

    // ---- Resource ----
    RESOURCE_NOT_FOUND(4001, "Resource not found", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXISTS(4002, "Resource already exists", HttpStatus.CONFLICT),
    METHOD_NOT_ALLOWED(4003, "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED),

    // ---- Server ----
    INTERNAL_ERROR(9999, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, @NotNull HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
