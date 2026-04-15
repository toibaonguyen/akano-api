package com.akano.api.controller;

import com.akano.api.dto.request.BookCreationRequest;
import com.akano.api.dto.response.ApiResponse;
import com.akano.api.dto.response.BookResponse;
import com.akano.api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookResponse> createBook(@Valid @RequestBody BookCreationRequest request) {
        // Hardcode user ID for createdBy and updatedBy for now per requirements
        UUID hardcodedUserId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        BookResponse response = bookService.createBook(request, hardcodedUserId);
        return ApiResponse.ok(response, "Book created successfully");
    }
}
