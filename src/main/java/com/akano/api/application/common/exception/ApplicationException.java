package com.akano.api.application.common.exception;

import java.util.Set;
import java.util.stream.Collectors;

import com.akano.api.domain.common.error.DomainError;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String applicationErrorCode;
    private final Set<String> domainErrorCodes;

    public ApplicationException(String applicationErrorCode, Set<DomainError> domainErrors) {
        this.applicationErrorCode = applicationErrorCode;
        this.domainErrorCodes = domainErrors.stream().map(DomainError::getDomainErrorCode).collect(Collectors.toSet());
    }
}
