package com.akano.api.domain.order;

import java.math.BigDecimal;
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
public class OrderDetail {

    
    
    
    private UUID id;

    
    private UUID orderId;

    
    private UUID bookId;

    
    private Integer quantity;

    
    private BigDecimal priceAtPurchase;

    
    
    private LocalDateTime createdAt;

    
    
    private LocalDateTime updatedAt;

    
    private UUID createdBy;

    
    private UUID updatedBy;
}
