package com.akano.api.service;

import com.akano.api.dto.request.BookCreationRequest;
import com.akano.api.dto.response.BookResponse;
import com.akano.api.exception.AppException;
import com.akano.api.exception.ErrorCode;
import com.akano.api.mapper.BookMapper;
import com.akano.api.repository.BookRepository;
import com.akano.api.repository.PublisherRepository;
import com.akano.api.entity.Book;
import com.akano.api.entity.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapper bookMapper;

    @Transactional
    public BookResponse createBook(BookCreationRequest request, UUID userId) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new AppException(ErrorCode.INVALID_FIELD, "Book with this ISBN already exists");
        }

        Publisher publisher = publisherRepository.findById(request.publisherId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_FIELD, "Publisher not found"));

        Book book = bookMapper.toEntity(request, publisher, userId);
        book.setId(UUID.randomUUID());
        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponse(savedBook);
    }
}
