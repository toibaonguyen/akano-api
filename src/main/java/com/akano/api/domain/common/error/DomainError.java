package com.akano.api.domain.common.error;

public abstract class DomainError {
    private final String domainErrorCode;
    protected DomainError(String domainErrorCode) {
        this.domainErrorCode = domainErrorCode;
    }
    public String getDomainErrorCode() {
        return domainErrorCode;
    }
}
