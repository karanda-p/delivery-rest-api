package com.itfb.fooddeliveryservice;

import com.itfb.fooddeliveryservice.component.MessageComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;

@SpringBootApplication
public class DeliveryRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryRestApiApplication.class, args);
    }

}
