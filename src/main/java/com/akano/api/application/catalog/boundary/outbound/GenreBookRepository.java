package com.akano.api.application.catalog.boundary.outbound;

import java.util.UUID;

import com.akano.api.domain.catalog.GenreBook;

public interface GenreBookRepository {
    GenreBook save(UUID bookId, UUID genreId, UUID requestUserId);
}
