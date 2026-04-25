package com.akano.api.domain.catalog;

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

public class BookImage {

    
    private BookImageId id;

    
    
    
    private Book book;

    
    private String url;

    
    
    private LocalDateTime createdAt;

    
    
    private LocalDateTime updatedAt;

    
    private UUID createdBy;

    
    private UUID updatedBy;
}
