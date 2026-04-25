package com.akano.api.infrastructure.exception;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.akano.api.application.common.exception.ApplicationErrorCode;
import com.akano.api.application.common.exception.ApplicationException;
import com.akano.api.presentation.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(ApplicationException ex) {
        String errorCode = ex.getApplicationErrorCode();
        Set<String> errors = ex.getDomainErrorCodes();

        ApiResponse<Void> response = ApiResponse.error(
                errorCode,
                errors);

        return ResponseEntity
                .status(ExceptionMap.getHttpStatus(errorCode))
                .body(response);
    }

    /**
     * Handle Bean Validation errors (e.g. @Valid on request body). Populates
     * the 'errors' field with field-specific messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        Set<String> fieldErrors = new HashSet<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.add(error.getDefaultMessage()));

        ApiResponse<Void> response = ApiResponse.error(ApplicationErrorCode.INVALID_FIELD, fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Handle 404 - resource/endpoint not found.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NoResourceFoundException ex) {
        String errorCode = InfrastructureErrorCode.ENDPOINT_NOT_FOUND;
        ApiResponse<Void> response = ApiResponse.error(errorCode);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * Handle 405 - HTTP method not supported.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        String errorCode = InfrastructureErrorCode.METHOD_NOT_ALLOWED;
        ApiResponse<Void> response = ApiResponse.error(errorCode);

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(response);
    }

    /**
     * Catch-all for unexpected exceptions. Sanitized message to avoid leaking
     * sensitive information.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUncaughtException(Exception ex) {
        String errorCode = InfrastructureErrorCode.INTERNAL_ERROR;
        ApiResponse<Void> response = ApiResponse.error(errorCode);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * Catch-all for unexpected exceptions. Sanitized message to avoid leaking
     * sensitive information.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleUncaughtException(RuntimeException ex) {
        String errorCode = InfrastructureErrorCode.INTERNAL_ERROR;
        ApiResponse<Void> response = ApiResponse.error(errorCode);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
