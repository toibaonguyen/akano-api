package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.AuthorRepository;
import com.akano.api.domain.catalog.Author;

@Repository
public interface AuthorJPARepository extends JpaRepository<Author, UUID>, AuthorRepository {
}
