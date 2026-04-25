package com.akano.api.application.catalog.boundary.outbound;

import java.util.UUID;

import com.akano.api.domain.catalog.AuthorBook;

public interface AuthorBookRepository {
    AuthorBook save(UUID authorId, UUID bookId, UUID userId);
}
