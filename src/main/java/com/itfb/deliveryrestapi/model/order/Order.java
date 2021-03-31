package com.itfb.deliveryrestapi.model.order;

import com.itfb.deliveryrestapi.model.Customer;
import com.itfb.deliveryrestapi.model.Restaurant;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "done_date")
    private String doneDate;

    @Column(name = "amount")
    private double amount;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItems;
}
