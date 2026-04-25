package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.BookRepository;
import com.akano.api.domain.catalog.Book;

@Repository
public interface BookJPARepository extends JpaRepository<Book, UUID>, BookRepository {
}
