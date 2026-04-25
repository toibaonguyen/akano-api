package com.akano.api.domain.catalog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    
    private UUID id;

    
    private String title;

    
    private String isbn;

    
    private String description;

    
    private Integer pageCount;

    private String language;

    private String dimensions;

    private BigDecimal weight;

    
    private String coverType;

    
    private LocalDate publishedDate;
    
    private UUID publisherId;

    
    
    private LocalDateTime createdAt;

    
    
    private LocalDateTime updatedAt;

    
    private UUID createdBy;

    
    private UUID updatedBy;

}
