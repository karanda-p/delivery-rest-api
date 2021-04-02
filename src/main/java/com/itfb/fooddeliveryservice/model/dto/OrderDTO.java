package com.itfb.fooddeliveryservice.model.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String address;
    private String status;
    private String creationDate;
    private String doneDate;
    private double amount;
}
