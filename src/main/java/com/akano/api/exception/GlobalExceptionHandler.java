package com.akano.api.exception;

import com.akano.api.dto.response.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handle custom AppException.
         */
        @ExceptionHandler(AppException.class)
        public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex) {
                ErrorCode errorCode = ex.getErrorCode();

                ApiResponse<Void> response = ApiResponse.error(
                                errorCode.getCode(),
                                ex.getMessage());

                return ResponseEntity
                                .status(errorCode.getHttpStatus())
                                .body(response);
        }

        /**
         * Handle Bean Validation errors (e.g. @Valid on request body).
         * Populates the 'errors' field with field-specific messages.
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
                Map<String, Object> fieldErrors = new LinkedHashMap<>();

                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

                ApiResponse<Void> response = ApiResponse.error(
                                ErrorCode.INVALID_FIELD.getCode(),
                                ErrorCode.INVALID_FIELD.getMessage(),
                                fieldErrors);

                return ResponseEntity
                                .status(ErrorCode.INVALID_FIELD.getHttpStatus())
                                .body(response);
        }

        /**
         * Handle 404 - resource/endpoint not found.
         */
        @ExceptionHandler(NoResourceFoundException.class)
        public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NoResourceFoundException ex) {
                ApiResponse<Void> response = ApiResponse.error(
                                ErrorCode.RESOURCE_NOT_FOUND.getCode(),
                                ErrorCode.RESOURCE_NOT_FOUND.getMessage());

                return ResponseEntity
                                .status(ErrorCode.RESOURCE_NOT_FOUND.getHttpStatus())
                                .body(response);
        }

        /**
         * Handle 405 - HTTP method not supported.
         */
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
                ApiResponse<Void> response = ApiResponse.error(
                                ErrorCode.METHOD_NOT_ALLOWED.getCode(),
                                ErrorCode.METHOD_NOT_ALLOWED.getMessage());

                return ResponseEntity
                                .status(ErrorCode.METHOD_NOT_ALLOWED.getHttpStatus())
                                .body(response);
        }

        /**
         * Catch-all for unexpected exceptions.
         * Sanitized message to avoid leaking sensitive information.
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Void>> handleUncaughtException(Exception ex) {
                ApiResponse<Void> response = ApiResponse.error(
                                ErrorCode.INTERNAL_ERROR.getCode(),
                                ErrorCode.INTERNAL_ERROR.getMessage());

                return ResponseEntity
                                .status(ErrorCode.INTERNAL_ERROR.getHttpStatus())
                                .body(response);
        }
}

