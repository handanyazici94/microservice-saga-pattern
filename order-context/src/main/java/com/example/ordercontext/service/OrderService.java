package com.example.ordercontext.service;


import com.example.ordercontext.dtos.OrderDTO;
import com.example.ordercontext.model.Order;
import com.example.ordercontext.model.OrderItem;
import com.example.ordercontext.model.Product;
import com.example.ordercontext.repository.OrderRepository;
import com.example.ordercontext.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ProductRepository productRepository;

    public void createOrder (OrderDTO orderDTO) {
       List<OrderItem> orderItemList = new ArrayList<>();

       orderDTO.items().forEach(item -> {
            Optional<Product> product = productRepository.findById(item.productId());
           if(product.isPresent()) {
               Product p = product.get();
               OrderItem orderItem = OrderItem.builder()
                       .productId(p.getId())
                       .productName(p.getName())
                       .quantity(item.quantity())
                       .build();
               orderItemService.saveOrderItem(orderItem);
               orderItemList.add(orderItem);
           } else {
               System.out.println("Product has not been found...");
           }
       });

        Order order = Order.builder()
                .items(orderItemList).build();
        orderRepository.save(order);

    }
}
