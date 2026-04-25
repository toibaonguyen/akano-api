package com.akano.api.presentation.catalog.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.akano.api.application.catalog.boundary.inbound.CreateBookInformationUseCase;
import com.akano.api.application.catalog.model.request.CreateBookRequestModel;
import com.akano.api.application.catalog.model.response.CreateBookResponseModel;
import com.akano.api.presentation.catalog.contract.BookCreationRequest;
import com.akano.api.presentation.catalog.contract.BookCreationResponse;
import com.akano.api.presentation.catalog.mapper.BookMapper;
import com.akano.api.presentation.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final CreateBookInformationUseCase createBookInformationUseCase;
    private final BookMapper bookMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<BookCreationResponse>> createBook(@Valid @RequestBody BookCreationRequest request) {
        UUID hardcodedUserId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        CreateBookRequestModel requestModel = bookMapper.toCreateBookRequestModel(request, hardcodedUserId);
        CreateBookResponseModel responseModel = createBookInformationUseCase.execute(requestModel);
        BookCreationResponse response = bookMapper.toResponse(responseModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(responseModel.getCode(), response));
    }
}
