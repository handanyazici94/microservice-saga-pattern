package com.example.ordercontext.dtos;


import java.util.List;

public record OrderDTO(List<OrderItemDTO> items) {
}
