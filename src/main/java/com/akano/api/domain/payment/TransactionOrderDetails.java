package com.akano.api.domain.payment;

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
public class TransactionOrderDetails {

    
    
    
    private UUID id;

    
    private UUID transactionOrderId;

    
    private UUID cartItemId;

    
    
    private LocalDateTime createdAt;

    
    
    private LocalDateTime updatedAt;

    
    private UUID createdBy;

    
    private UUID updatedBy;
}
