package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.repository.PaymentDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentIntegrationService paymentIntegrationService;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Transactional
    public Order commitPayment(Order sentOrder){
        Order order = orderService.getOrderById(sentOrder.getId());
        PaymentDetails paymentDetails = paymentIntegrationService.commitPayment(orderMapper.domainToDto(order));
        paymentDetails.setDoneDate(LocalDateTime.now());
        paymentDetailsRepository.save(paymentDetails);
        order.setPaymentDetails(paymentDetails);
        order.setPaymentId(paymentDetails.getId());
        order.setStatus(OrderStatus.PAID);

        return orderService.saveOrder(order);
    }

}
