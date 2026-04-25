package com.akano.api.application.catalog.boundary.outbound;

import java.util.UUID;

public interface PublisherRepository {
    boolean existsById(UUID id);
}
