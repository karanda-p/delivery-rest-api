package com.itfb.fooddeliveryservice.service.payment;

import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final RabbitTemplate template;
    private final Exchange exchange;

    @Value("${message.payment-routing-key}")
    private String routingKey;

    public void commitPayment(OrderDTO orderDTO) {
        template.convertAndSend(exchange.getName(), routingKey, orderDTO);
    }
}
