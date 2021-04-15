package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderMapper orderMapper;

    @PostMapping("/pay")
    public OrderDTO commitPayment(@RequestBody Order order) {
        return orderMapper.domainToDto(paymentService.commitPayment(order));
    }
}
