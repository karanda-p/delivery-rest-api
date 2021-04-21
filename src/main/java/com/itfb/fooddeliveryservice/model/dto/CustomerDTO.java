package com.itfb.fooddeliveryservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private Long id;
    private String login;
    private String password;
    private String phone;
    private Long cartId;
    private String email;
    private boolean emailNotification;
    private boolean phoneNotification;
}
