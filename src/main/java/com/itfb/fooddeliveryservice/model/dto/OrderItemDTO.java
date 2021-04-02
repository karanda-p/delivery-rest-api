package com.itfb.fooddeliveryservice.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;

}
