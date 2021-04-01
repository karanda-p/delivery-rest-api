package com.itfb.fooddeliveryservice.model.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long id;
    private Long productId;
    private int quantity;

}
