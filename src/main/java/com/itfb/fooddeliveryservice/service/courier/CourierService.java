package com.itfb.fooddeliveryservice.service.courier;

import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class CourierService {

    private final RabbitTemplate template;
    private final Exchange exchange;

    @Value("${message.courier-routing-key}")
    private String routingKey;

    public void sendOrderToCourierService(OrderDTO orderDTO) {
        template.convertAndSend(exchange.getName(), routingKey, orderDTO);
    }
}
