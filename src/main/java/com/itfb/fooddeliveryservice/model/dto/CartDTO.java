package com.itfb.fooddeliveryservice.model.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class CartDTO {

    private Long id;
    private String creationDate;
    private Collection<CartItemDTO> cartItems;
}
