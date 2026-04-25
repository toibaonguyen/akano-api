package com.akano.api.application.catalog.boundary.inbound;

import com.akano.api.application.catalog.model.request.CreateBookRequestModel;
import com.akano.api.application.catalog.model.response.CreateBookResponseModel;

public interface CreateBookInformationUseCase {
    CreateBookResponseModel execute(CreateBookRequestModel request);
}