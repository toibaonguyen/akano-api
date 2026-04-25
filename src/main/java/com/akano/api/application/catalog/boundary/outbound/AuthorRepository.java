package com.akano.api.application.catalog.boundary.outbound;

import java.util.UUID;

public interface AuthorRepository {

    boolean existsById(UUID id);
}
