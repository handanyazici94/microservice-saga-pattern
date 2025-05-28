package com.example.productcontext.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductRabbitMQConfig {

    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(rabbitMQProperties.getExchanges().get("product"));
    }

    @Bean
    public Queue orderProductCreatedQueue() {
        return new Queue(rabbitMQProperties.getQueues().getOrder().get("productCreated"));
    }

    @Bean
    public Binding productCreatedBinding() {
        return BindingBuilder
                .bind(orderProductCreatedQueue())
                .to(productExchange())
                .with(rabbitMQProperties.getRoutingKeys().getProduct().get("created"));
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
