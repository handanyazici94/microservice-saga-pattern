package com.example.productcontext.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductCreateRequestDTO(String name, String description, BigDecimal price) {


}
