package com.itfb.fooddeliveryservice;

import com.itfb.fooddeliveryservice.component.LdapProperties;
import com.itfb.fooddeliveryservice.component.UserDetailsAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        LdapProperties.class,
        UserDetailsAuthProperties.class
})
public class DeliveryRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryRestApiApplication.class, args);
    }

}
