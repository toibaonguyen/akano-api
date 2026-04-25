package com.akano.api.presentation.catalog.contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookCreationRequest(
        @NotBlank(message = "ERROR/MISSING_TITLE")
        String title,
        @NotBlank(message = "ERROR/MISSING_ISBN")
        String isbn,
        @NotBlank(message = "ERROR/MISSING_DESCRIPTION")
        String description,
        @Positive(message = "ERROR/INVALID_PAGE_COUNT")
        @JsonProperty("page_count")
        Integer pageCount,
        String language,
        String dimensions,
        @Positive(message = "ERROR/INVALID_WEIGHT")
        BigDecimal weight,
        @JsonProperty("cover_type")
        String coverType,
        @NotNull(message = "ERROR/MISSING_PUBLISHED_DATE")
        @JsonProperty("published_date")
        LocalDate publishedDate,
        @NotNull(message = "ERROR/MISSING_PUBLISHER_ID")
        @JsonProperty("publisher_id")
        UUID publisherId,
        @NotNull(message = "ERROR/MISSING_AUTHOR_IDS")
        @JsonProperty("author_ids")
        Set<UUID> authorIds,
        @NotNull(message = "ERROR/MISSING_GENRE_IDS")
        @JsonProperty("genre_ids")
        Set<UUID> genreIds) {

}
