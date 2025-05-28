package com.example.productcontext.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {

    private Map<String, String> exchanges;
    private Queues queues;
    private RoutingKeys routingKeys;

    //region inner classes - Queue & RoutingKeys

    @Data
    public static class Queues {
        private Map<String, String> order;
    }

    @Data
    public static class RoutingKeys {
        private Map<String, String> product;
    }

    //endregion


}
