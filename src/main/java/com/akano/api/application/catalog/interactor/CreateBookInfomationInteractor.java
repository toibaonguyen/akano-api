package com.akano.api.application.catalog.interactor;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.akano.api.application.catalog.boundary.inbound.CreateBookInformationUseCase;
import com.akano.api.application.catalog.boundary.outbound.AuthorBookRepository;
import com.akano.api.application.catalog.boundary.outbound.AuthorRepository;
import com.akano.api.application.catalog.boundary.outbound.BookRepository;
import com.akano.api.application.catalog.boundary.outbound.GenreBookRepository;
import com.akano.api.application.catalog.boundary.outbound.GenreRepository;
import com.akano.api.application.catalog.boundary.outbound.PublisherRepository;
import com.akano.api.application.catalog.model.request.CreateBookRequestModel;
import com.akano.api.application.catalog.model.response.CreateBookResponseModel;
import com.akano.api.application.common.exception.ApplicationErrorCode;
import com.akano.api.application.common.exception.ApplicationException;
import com.akano.api.domain.catalog.Book;
import com.akano.api.domain.catalog.error.AuthorNotExistError;
import com.akano.api.domain.catalog.error.BookAlreadyExistError;
import com.akano.api.domain.catalog.error.GenreNotExistError;
import com.akano.api.domain.catalog.error.PublisherNotExistError;

import lombok.RequiredArgsConstructor;

@Mapper
interface CreateBookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", source = "requestModel.requestUserId")
    @Mapping(target = "updatedBy", source = "requestModel.requestUserId")
    @Mapping(target = "publisherId", source = "publisherId")
    Book toEntity(CreateBookRequestModel requestModel);

    @Mapping(target = "data.id", source = "id")
    CreateBookResponseModel toResponse(Book book);
}

@RequiredArgsConstructor
public class CreateBookInfomationInteractor implements CreateBookInformationUseCase {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;
    private final GenreRepository genreRepository;
    private final GenreBookRepository bookGenreRepository;

    @Override
    public CreateBookResponseModel execute(CreateBookRequestModel request) {
        CreateBookMapper mapper = Mappers.getMapper(CreateBookMapper.class);
        boolean isBookExist = bookRepository.existsByIsbn(request.getIsbn());
        if (isBookExist) {
            throw new ApplicationException(ApplicationErrorCode.INVALID_FIELD, Set.of(new BookAlreadyExistError()));
        }
        boolean isPublisherExist = publisherRepository.existsById(request.getPublisherId());
        if (!isPublisherExist) {
            throw new ApplicationException(ApplicationErrorCode.INVALID_FIELD, Set.of(new PublisherNotExistError()));
        }
        boolean areAuthorsExist = request.getAuthorIds().stream().allMatch(authorRepository::existsById);
        if (!areAuthorsExist) {
            throw new ApplicationException(ApplicationErrorCode.INVALID_FIELD, Set.of(new AuthorNotExistError()));
        }
        boolean areGenresExist = request.getGenreIds().stream().allMatch(genreRepository::existsById);
        if (!areGenresExist) {
            throw new ApplicationException(ApplicationErrorCode.INVALID_FIELD, Set.of(new GenreNotExistError()));
        }
        Book savedBook = bookRepository.save(mapper.toEntity(request));
        request.getAuthorIds().forEach(authorId -> authorBookRepository.save(authorId, savedBook.getId(), request.getRequestUserId()));
        request.getGenreIds().forEach(genreId -> bookGenreRepository.save(savedBook.getId(), genreId, request.getRequestUserId()));
        return mapper.toResponse(savedBook);
    }
}
