package com.akano.api.application.catalog.boundary.outbound;

import com.akano.api.domain.catalog.Book;

public interface BookRepository {
    boolean existsByIsbn(String isbn);
    Book save(Book book);
}
