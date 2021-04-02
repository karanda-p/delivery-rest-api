package com.itfb.fooddeliveryservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String address;
    private OrderStatus status;
    private String creationDate;
    private String doneDate;
    private double amount;
}
