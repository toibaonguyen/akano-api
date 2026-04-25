package com.akano.api.domain.catalog.error;

import com.akano.api.domain.common.error.DomainError;

public class GenreNotExistError extends DomainError {
    public GenreNotExistError() {
        super("ERROR/GENRE_NOT_EXIST");
    }
}
