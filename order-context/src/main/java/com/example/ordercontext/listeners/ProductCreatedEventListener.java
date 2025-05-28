package com.example.ordercontext.listeners;


import com.example.ordercontext.events.ProductCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedEventListener {

    @RabbitListener(queues = "${rabbitmq.queues.order.productCreated}")
    public void handleProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        System.out.println("handle product created event..");
    }
}
