package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.AuthorBookRepository;
import com.akano.api.domain.catalog.AuthorBook;
import com.akano.api.domain.catalog.AuthorBookId;

@Repository
public interface AuthorBookJPARepository extends JpaRepository<AuthorBook, AuthorBookId>, AuthorBookRepository {

    @Override
    @Query(value = "INSERT INTO author_book (author_id, book_id, created_by) VALUES (:authorId, :bookId, :userId) RETURNING *", nativeQuery = true)
    AuthorBook save(UUID authorId, UUID bookId, UUID userId);
}
