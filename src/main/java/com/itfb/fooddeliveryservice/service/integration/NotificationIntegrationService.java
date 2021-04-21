package com.itfb.fooddeliveryservice.service.integration;

import com.itfb.fooddeliveryservice.model.NotificationMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notificationIntegrationService", url = "${app.integration.notification.url}")
public interface NotificationIntegrationService {

    @PostMapping("/notify")
    public void sendNotification(NotificationMessage message);

}
