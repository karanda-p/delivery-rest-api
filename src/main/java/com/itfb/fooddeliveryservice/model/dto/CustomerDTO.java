package com.itfb.fooddeliveryservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private Long id;
    private String login;
    private String password;
    private String phone;
    private Long cartId;
}
