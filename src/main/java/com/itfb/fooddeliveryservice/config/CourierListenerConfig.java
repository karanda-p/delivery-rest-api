package com.itfb.fooddeliveryservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CourierListenerConfig {

    @Value("${message.courier-queue}")
    private String queueName;

    @Value("${message.fds-courier-routing-key}")
    private String routingKey;


    @Bean
    public Queue eventCourierReceivingQueue() {
        if (queueName == null) {
            throw new IllegalStateException("No queue to listen to! Please specify the name of the queue to listen to with the property 'subscriber.queue'");
        }
        return new Queue(queueName);
    }

    @Bean
    public Binding binding(Queue eventCourierReceivingQueue, TopicExchange receiverExchange) {
        if (routingKey == null) {
            throw new IllegalStateException("No events to listen to! Please specify the routing key for the events to listen to with the property 'subscriber.routingKey' (see EventPublisher for available routing keys).");
        }
        return BindingBuilder
                .bind(eventCourierReceivingQueue)
                .to(receiverExchange)
                .with(routingKey);
    }

}
