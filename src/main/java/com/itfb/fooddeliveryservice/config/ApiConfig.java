package com.itfb.fooddeliveryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenApi(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion) {
        return new OpenAPI().info(new Info().title("FDS API")
                .version(appVersion)
                .description(appDescription)
                .contact(new Contact().name("p.karanda")
                        .email("p.karanda@itfbgroup.ru")));
    }
}
