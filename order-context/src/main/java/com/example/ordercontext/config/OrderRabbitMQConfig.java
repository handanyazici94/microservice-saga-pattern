package com.example.ordercontext.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class OrderRabbitMQConfig {
    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(rabbitMQProperties.getExchanges().get("product"));
    }

    @Bean
    public DirectExchange orderDLX() {
        return new DirectExchange(rabbitMQProperties.getExchanges().get("dlx"));
    }

    @Bean
    public Queue retryQueue() {
        return QueueBuilder.durable(rabbitMQProperties.getQueues().getOrder().get("dlx")).build();
    }


    @Bean
    public Queue orderProductCreatedQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 5000);
        arguments.put("x-dead-letter-exchange", rabbitMQProperties.getExchanges().get("dlx"));
        arguments.put("x-dead-letter-routing-key", rabbitMQProperties.getRoutingKeys().getOrder().get("retry"));

        return QueueBuilder.durable(rabbitMQProperties.getQueues().getOrder().get("productCreated"))
                            .withArguments(arguments).build();
    }

    @Bean
    public Binding productCreatedBinding() {
        return BindingBuilder
                .bind(orderProductCreatedQueue())
                .to(productExchange())
                .with(rabbitMQProperties.getRoutingKeys().getProduct().get("created"));
    }

    @Bean
    public Binding retryBinding() {
        return BindingBuilder.bind(retryQueue())
                .to(orderDLX())
                .with(rabbitMQProperties.getRoutingKeys().getOrder().get("retry"));
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper jsonMapper) {
        return new Jackson2JsonMessageConverter(jsonMapper);
    }
}

