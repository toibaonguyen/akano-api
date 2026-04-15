package com.akano.api.mapper;

import com.akano.api.dto.request.BookCreationRequest;
import com.akano.api.dto.response.BookResponse;
import com.akano.api.entity.Book;
import com.akano.api.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "publisher", source = "publisher")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "updatedBy", source = "createdBy")
    Book toEntity(BookCreationRequest request, Publisher publisher, UUID createdBy);

    @Mapping(target = "publisherId", source = "publisher.id")
    BookResponse toResponse(Book entity);
}
