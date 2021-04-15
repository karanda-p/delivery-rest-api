package com.itfb.fooddeliveryservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itfb.fooddeliveryservice.model.domain.payment.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDetailsDTO {

    private Long id;
    private LocalDateTime doneDate;
    private double amount;
    private PaymentStatus paymentStatus;
}
