package com.example.ordercontext.listeners;

import com.example.ordercontext.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreatedEventRetryListener {

    @RabbitListener(queues = "${rabbitmq.queues.order.dlx}")
    public void handleProductCreatedEventRetry(ProductCreatedEvent productCreatedEvent) {
        System.out.println("Listening DLX Queue..");
    }

}
