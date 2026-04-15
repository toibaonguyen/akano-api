package com.akano.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookResponse(
        UUID id,
        String title,
        String isbn,
        String description,
        @JsonProperty("page_count")
        Integer pageCount,
        String language,
        String dimensions,
        BigDecimal weight,
        String coverType,
        @JsonProperty("published_date")
        LocalDate publishedDate,
        @JsonProperty("publisher_id")   
        UUID publisherId,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        LocalDateTime updatedAt,
        @JsonProperty("created_by")
        UUID createdBy,
        @JsonProperty("updated_by")
        UUID updatedBy
) {}
