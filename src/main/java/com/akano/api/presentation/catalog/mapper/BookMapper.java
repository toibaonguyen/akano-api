package com.akano.api.presentation.catalog.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.akano.api.application.catalog.model.request.CreateBookRequestModel;
import com.akano.api.application.catalog.model.response.CreateBookResponseModel;
import com.akano.api.presentation.catalog.contract.BookCreationRequest;
import com.akano.api.presentation.catalog.contract.BookCreationResponse;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "requestUserId", source = "requestUserId")
    CreateBookRequestModel toCreateBookRequestModel(BookCreationRequest request, UUID requestUserId);

    @Mapping(target = "id", source = "responseModel.data.id")
    BookCreationResponse toResponse(CreateBookResponseModel responseModel);
}
