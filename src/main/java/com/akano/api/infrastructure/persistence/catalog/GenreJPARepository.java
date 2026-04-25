package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.GenreRepository;
import com.akano.api.domain.catalog.Genre;

@Repository
public interface GenreJPARepository extends JpaRepository<Genre, UUID>, GenreRepository {
}
