package com.akano.api.domain.catalog.error;

import com.akano.api.domain.common.error.DomainError;

public class BookAlreadyExistError extends DomainError {
    public BookAlreadyExistError() {
        super("ERROR/BOOK_ALREADY_EXIST");
    }
}
