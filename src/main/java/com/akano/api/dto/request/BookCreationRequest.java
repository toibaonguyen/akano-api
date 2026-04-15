package com.akano.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookCreationRequest(
        @NotBlank(message = "title must not be blank")
        String title,

        @NotBlank(message = "isbn must not be blank")
        String isbn,

        @NotBlank(message = "description must not be blank")
        String description,

        @Positive(message = "page_count must be positive")
        @JsonProperty("page_count")     
        Integer pageCount,

        String language,

        String dimensions,

        @Positive(message = "weight must be positive")
        BigDecimal weight,

        @JsonProperty("cover_type")
        String coverType,

        @NotNull(message = "published_date must not be null")
        @JsonProperty("published_date")
        LocalDate publishedDate,

        @NotNull(message = "publisher_id must not be null")
        @JsonProperty("publisher_id")
        UUID publisherId
) {}
