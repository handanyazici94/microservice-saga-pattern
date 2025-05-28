package com.example.productcontext.services;

import com.example.productcontext.config.ProductRabbitMQConfig;
import com.example.productcontext.config.RabbitMQProperties;
import com.example.productcontext.dtos.ProductCreateRequestDTO;
import com.example.productcontext.dtos.ProductListResponseDTO;
import com.example.productcontext.events.ProductCreatedEvent;
import com.example.productcontext.models.Product;
import com.example.productcontext.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ProductRabbitMQConfig rabbitMQConfig;
    private final RabbitMQProperties rabbitMQProperties;

    public void createProduct(ProductCreateRequestDTO productCreateRequestDTO) {
        Product product = Product.builder()
                .name(productCreateRequestDTO.name())
                .description(productCreateRequestDTO.description())
                .price(productCreateRequestDTO.price()).build();
        productRepository.save(product);

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        // region send product created event
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchanges().get("product"),
                rabbitMQProperties.getRoutingKeys().getProduct().get("created"),
                productCreatedEvent);
        // endregion
    }

    public List<ProductListResponseDTO> getAllProducts () {
        return productRepository.findAll().stream()
                .map(product -> ProductListResponseDTO.builder()
                     .id(product.getId())
                     .description(product.getDescription())
                     .name(product.getName())
                     .price(product.getPrice()).build())
                .toList();
    }
}
