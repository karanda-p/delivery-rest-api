package com.itfb.fooddeliveryservice.model.domain.payment;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @SequenceGenerator(name = "payment_details_id_gen", sequenceName = "payment_details_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_details_id_gen")
    private Long id;

    @Column(name = "done_date")
    private LocalDateTime doneDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private PaymentStatus paymentStatus;

    @Column(name = "details")
    private Long details;

}
