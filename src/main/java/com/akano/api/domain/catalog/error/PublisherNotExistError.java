package com.akano.api.domain.catalog.error;

import com.akano.api.domain.common.error.DomainError;

public class PublisherNotExistError extends DomainError {
    public PublisherNotExistError() {
        super("ERROR/PUBLISHER_NOT_EXIST");
    }
}
