package com.itfb.fooddeliveryservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationMessage {

    private String customerName;
    private String email;
    private String phone;
    private boolean emailEnabled;
    private boolean phoneEnabled;
    private Long orderId;
    private OrderStatus orderStatus;

    public NotificationMessage(Customer customer, Order order){
        this.customerName = customer.getLogin();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.emailEnabled = customer.isEmailNotification();
        this.phoneEnabled = customer.isPhoneNotification();
        this.orderId = order.getId();
        this.orderStatus = order.getStatus();
    }

}
