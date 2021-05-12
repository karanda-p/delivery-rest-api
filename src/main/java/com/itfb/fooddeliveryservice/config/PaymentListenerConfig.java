package com.itfb.fooddeliveryservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentListenerConfig {

    @Value("${message.payment-queue}")
    private String queueName;

    @Value("${message.fds-payment-routing-key}")
    private String routingKey;

    @Bean
    public Queue eventPaymentReceivingQueue() {
        if (queueName == null) {
            throw new IllegalStateException("No queue to listen to! Please specify the name of the queue to listen to with the property 'subscriber.queue'");
        }
        return new Queue(queueName);
    }

    @Bean
    public Binding binding(Queue eventPaymentReceivingQueue, TopicExchange receiverExchange) {
        if (routingKey == null) {
            throw new IllegalStateException("No events to listen to! Please specify the routing key for the events to listen to with the property 'subscriber.routingKey' (see EventPublisher for available routing keys).");
        }
        return BindingBuilder
                .bind(eventPaymentReceivingQueue)
                .to(receiverExchange)
                .with(routingKey);
    }

}
