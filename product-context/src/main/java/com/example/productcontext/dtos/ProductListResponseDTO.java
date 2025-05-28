package com.example.productcontext.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductListResponseDTO(String id, String name, String description, BigDecimal price) {
}
