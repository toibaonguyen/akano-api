package com.akano.api.application.catalog.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.akano.api.application.common.BaseRequestModel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateBookRequestModel implements BaseRequestModel<UUID> {

    String title;
    String isbn;
    String description;
    Integer pageCount;
    String language;
    String dimensions;
    BigDecimal weight;
    String coverType;
    LocalDate publishedDate;
    UUID publisherId;
    Set<UUID> authorIds;
    Set<UUID> genreIds;
    UUID requestUserId;
}
