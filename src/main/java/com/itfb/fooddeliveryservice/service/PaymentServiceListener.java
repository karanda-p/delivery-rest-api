package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PaymentServiceListener {

    @RabbitListener(queues = "${message.payment-queue}")
    public void payment(PaymentDetails paymentDetails){

    }
}
