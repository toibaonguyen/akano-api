package com.akano.api.service;

import com.akano.api.dto.request.BookCreationRequest;
import com.akano.api.dto.response.BookResponse;
import com.akano.api.entity.Book;
import com.akano.api.entity.Publisher;
import com.akano.api.exception.AppException;
import com.akano.api.exception.ErrorCode;
import com.akano.api.mapper.BookMapper;
import com.akano.api.repository.BookRepository;
import com.akano.api.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private BookCreationRequest request;
    private UUID userId;
    private Publisher publisher;
    private Book book;
    private BookResponse bookResponse;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        UUID publisherId = UUID.randomUUID();

        request = new BookCreationRequest(
                "Effective Java",
                "978-0134685991",
                "A comprehensive guide to best practices in Java.",
                412,
                "English",
                "9 x 7 x 1",
                new BigDecimal("1.5"),
                "PAPERBACK",
                LocalDate.of(2017, 12, 27),
                publisherId
        );

        publisher = new Publisher();
        publisher.setId(publisherId);
        publisher.setName("Addison-Wesley");

        book = new Book();
        book.setId(UUID.randomUUID());
        book.setIsbn(request.isbn());
        book.setPublisher(publisher);

        bookResponse = new BookResponse(
                book.getId(),
                request.title(),
                request.isbn(),
                request.description(),
                request.pageCount(),
                request.language(),
                request.dimensions(),
                request.weight(),
                request.coverType(),
                request.publishedDate(),
                publisherId,
                null,
                null,
                userId,
                userId
        );
    }

    @Test
    @DisplayName("Should successfully create a book when request is valid")
    void shouldCreateBookSuccessfully() {
        // Given
        given(bookRepository.existsByIsbn(request.isbn())).willReturn(false);
        given(publisherRepository.findById(request.publisherId())).willReturn(Optional.of(publisher));
        given(bookMapper.toEntity(request, publisher, userId)).willReturn(book);
        given(bookRepository.save(book)).willReturn(book);
        given(bookMapper.toResponse(book)).willReturn(bookResponse);

        // When
        BookResponse response = bookService.createBook(request, userId);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.isbn()).isEqualTo(request.isbn());
        verify(bookRepository).existsByIsbn(request.isbn());
        verify(publisherRepository).findById(request.publisherId());
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("Should throw AppException when Publisher is not found")
    void shouldThrowExceptionWhenPublisherNotFound() {
        // Given
        given(bookRepository.existsByIsbn(request.isbn())).willReturn(false);
        given(publisherRepository.findById(request.publisherId())).willReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> bookService.createBook(request, userId))
                .isInstanceOf(AppException.class)
                .hasMessageContaining(ErrorCode.RESOURCE_NOT_FOUND.getMessage())
                .extracting("errorCode").isEqualTo(ErrorCode.RESOURCE_NOT_FOUND);

        verify(bookRepository).existsByIsbn(request.isbn());
        verify(publisherRepository).findById(request.publisherId());
        // Verify mapper and save are never called
        verify(bookMapper, org.mockito.Mockito.never()).toEntity(any(), any(), any());
        verify(bookRepository, org.mockito.Mockito.never()).save(any());
    }

    @Test
    @DisplayName("Should throw AppException when ISBN already exists")
    void shouldThrowExceptionWhenIsbnExists() {
        // Given
        given(bookRepository.existsByIsbn(request.isbn())).willReturn(true);

        // When & Then
        assertThatThrownBy(() -> bookService.createBook(request, userId))
                .isInstanceOf(AppException.class)
                .hasMessageContaining(ErrorCode.RESOURCE_ALREADY_EXISTS.getMessage())
                .extracting("errorCode").isEqualTo(ErrorCode.RESOURCE_ALREADY_EXISTS);

        verify(bookRepository).existsByIsbn(request.isbn());
        // Verify publisher lookup, mapper, and save are never called
        verify(publisherRepository, org.mockito.Mockito.never()).findById(any());
        verify(bookMapper, org.mockito.Mockito.never()).toEntity(any(), any(), any());
        verify(bookRepository, org.mockito.Mockito.never()).save(any());
    }
}
