package com.example.ordercontext.service;

import com.example.ordercontext.model.OrderItem;
import com.example.ordercontext.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void saveOrderItem (OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
