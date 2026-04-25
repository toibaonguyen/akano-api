package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.GenreBookRepository;
import com.akano.api.domain.catalog.GenreBook;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface GenreBookJPARepository extends JpaRepository<GenreBook, UUID>, GenreBookRepository {
    
    @Override
    @Query(value = "INSERT INTO genre_book (book_id, genre_id, created_by) VALUES (:bookId, :genreId, :requestUserId) RETURNING *", nativeQuery = true)
    GenreBook save(UUID bookId, UUID genreId, UUID requestUserId);
}
