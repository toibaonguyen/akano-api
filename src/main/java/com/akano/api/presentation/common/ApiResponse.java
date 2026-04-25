package com.akano.api.presentation.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String code;
    private Set<String> errors;
    private T data;

    @Builder.Default
    private long timestamp = Instant.now().toEpochMilli();

    /**
     * Success response with code, data.
     */
    public static <T> ApiResponse<T> ok(String code, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(code)
                .data(data)
                .build();
    }

    /**
     * Success response with code, data.
     */
    public static <T> ApiResponse<T> ok(String code) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(code)
                .build();
    }

    /**
     * Error response with code, field-level errors.
     */
    public static <T> ApiResponse<T> error(String code, Set<String> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .errors(errors)
                .build();
    }

    /**
     * Error response with code and message only.
     */
    public static <T> ApiResponse<T> error(String code) {
        return ApiResponse.<T>builder()
                .success(false) 
                .code(code)
                .build();
    }
}
