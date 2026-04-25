package com.akano.api.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.akano.api.application.common.exception.ApplicationErrorCode;

public class ExceptionMap {

    private static final Map<String, HttpStatus> ERROR_MAPPING = Map.of(
            ApplicationErrorCode.INVALID_FIELD, HttpStatus.BAD_REQUEST, // 400
            ApplicationErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, // 404
            ApplicationErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, // 401
            ApplicationErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, // 403
            InfrastructureErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, // 500
            InfrastructureErrorCode.ENDPOINT_NOT_FOUND, HttpStatus.NOT_FOUND, // 404
            InfrastructureErrorCode.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED // 405
    );

    public static HttpStatus getHttpStatus(String applicationErrorCode) {
        return ERROR_MAPPING.get(applicationErrorCode);
    }
}
