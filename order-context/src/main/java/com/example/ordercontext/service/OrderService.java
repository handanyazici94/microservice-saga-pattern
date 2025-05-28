package com.example.ordercontext.service;


import com.example.ordercontext.dtos.OrderDTO;
import com.example.ordercontext.model.Order;
import com.example.ordercontext.model.OrderItem;
import com.example.ordercontext.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder (OrderDTO orderDTO) {
//        orderDTO.items().stream().map(item -> {
//            OrderItem orderItem = OrderItem.builder()
//                    .productId(item.productId())
//                    .quantity(item.quantity())
//                    .
//        })
//        Order order = Order.builder().items()
     //   orderRepository.save(order);
    }
}
