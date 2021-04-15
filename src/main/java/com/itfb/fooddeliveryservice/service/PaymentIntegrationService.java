package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(url = "${app.integration.payment.url}", name = "paymentIntegration")
public interface PaymentIntegrationService {

    @PostMapping("/pay")
    public PaymentDetails commitPayment(OrderDTO orderDTO);
}
