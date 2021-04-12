package com.itfb.fooddeliveryservice.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "app.user-details-auth.test")
public class UserDetailsAuthProperties {

    private boolean enabled;

}
