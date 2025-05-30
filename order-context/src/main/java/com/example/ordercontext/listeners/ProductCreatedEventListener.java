package com.example.ordercontext.listeners;


import com.example.ordercontext.events.ProductCreatedEvent;
import com.example.ordercontext.model.Product;
import com.example.ordercontext.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreatedEventListener {

    private final ProductRepository productRepository;

    @RabbitListener(queues = "${rabbitmq.queues.order.dlx}")
    public void handleProductCreatedEventRetry(ProductCreatedEvent productCreatedEvent) {
        try {
            Product product = Product.builder()
                    .id(productCreatedEvent.id())
                    .name(productCreatedEvent.name())
                    .description(productCreatedEvent.description())
                    .price(productCreatedEvent.price()).build();

            productRepository.save(product);

            System.out.println("handle product created event..");
            throw new RuntimeException();
        } catch (Exception ex) {
            System.out.println("ProductCreatedEvent could not been processed");
            throw ex;
        }

    }
}
