package com.akano.api.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Address {

    
    private UUID id;

    
    private UUID accountId;

    
    private String recipientName;

    
    private String phone;

    
    private String street;

    
    private Boolean isDefault;

    
    
    private LocalDateTime createdAt;

    
    
    private LocalDateTime updatedAt;

    
    private UUID createdBy;

    
    private UUID updatedBy;

}
