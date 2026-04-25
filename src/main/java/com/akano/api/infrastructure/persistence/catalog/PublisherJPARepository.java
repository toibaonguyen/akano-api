package com.akano.api.infrastructure.persistence.catalog;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akano.api.application.catalog.boundary.outbound.PublisherRepository;
import com.akano.api.domain.catalog.Publisher;

@Repository
public interface PublisherJPARepository extends JpaRepository<Publisher, UUID>, PublisherRepository {
}
