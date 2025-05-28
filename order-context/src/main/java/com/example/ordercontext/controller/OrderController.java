package com.example.ordercontext.controller;


import com.example.ordercontext.dtos.OrderDTO;
import com.example.ordercontext.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        //orderService.createOrder(orderDTO);
    }
}