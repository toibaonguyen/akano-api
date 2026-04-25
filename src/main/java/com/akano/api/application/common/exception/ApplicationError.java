package com.akano.api.application.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationError {
    private final String applicationErrorCode;
}
