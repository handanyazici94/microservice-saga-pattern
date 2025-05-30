package com.example.ordercontext.events;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductCreatedEvent(String id, String name, String description, BigDecimal price)
{ }

