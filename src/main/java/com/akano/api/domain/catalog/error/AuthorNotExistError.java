package com.akano.api.domain.catalog.error;

import com.akano.api.domain.common.error.DomainError;

public class AuthorNotExistError extends DomainError {
    public AuthorNotExistError() {
        super("ERROR/AUTHOR_NOT_EXIST");
    }
}
