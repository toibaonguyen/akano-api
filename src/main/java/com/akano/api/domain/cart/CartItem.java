package com.akano.api.domain.cart;

import java.time.LocalDateTime;
import java.util.UUID;

import com.akano.api.domain.catalog.Product;

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

public class CartItem {

    private UUID id;

    private UUID accountId;

    private Product product;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID createdBy;

    private UUID updatedBy;
}
