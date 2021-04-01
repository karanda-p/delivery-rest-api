package com.itfb.fooddeliveryservice.model.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String login;
    private String password;
    private String phone;
    private Long cartId;
}
