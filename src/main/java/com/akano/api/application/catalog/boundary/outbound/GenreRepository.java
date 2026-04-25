package com.akano.api.application.catalog.boundary.outbound;

import java.util.UUID;

public interface GenreRepository {
    boolean existsById(UUID id);
}
