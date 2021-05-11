package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.repository.OrderRepository;
import com.itfb.fooddeliveryservice.repository.PaymentDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PaymentServiceListener {

    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderRepository orderRepository;

    @RabbitListener(queues = "${message.payment-queue}")
    public void payment(PaymentDetails paymentDetails){
        paymentDetails.setDoneDate(LocalDateTime.now());
        PaymentDetails savedPaymentDetails = paymentDetailsRepository.save(paymentDetails);
    }
}
