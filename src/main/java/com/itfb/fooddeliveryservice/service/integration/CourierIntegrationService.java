package com.itfb.fooddeliveryservice.service.integration;

import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "courierIntegrationService", url = "${app.integration.courier.url}")
public interface CourierIntegrationService {

    @PostMapping("/task")
    public void sendOrderToCourierService(OrderDTO order);
}
