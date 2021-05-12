package com.itfb.fooddeliveryservice.service.payment;

import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PaymentServiceListener {

    private final OrderService orderService;

    @RabbitListener(queues = "${message.payment-queue}")
    public void payment(PaymentDetails paymentDetails) throws IOException {
        orderService.changeOrderStatusToPaid(paymentDetails);
    }
}
