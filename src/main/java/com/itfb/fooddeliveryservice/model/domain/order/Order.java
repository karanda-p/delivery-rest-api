package com.itfb.fooddeliveryservice.model.domain.order;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.model.domain.payment.PaymentDetails;
import lombok.Getter;
import lombok.Setter;
import com.itfb.fooddeliveryservice.model.domain.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @SequenceGenerator(sequenceName = "order_seq", name = "order_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private Long restaurantId;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "done_date")
    private LocalDateTime doneDate;

    @Column(name = "amount")
    private double amount;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_details_id", insertable = false, updatable = false)
    private PaymentDetails paymentDetails;

    @Column(name = "payment_details_id")
    private Long paymentId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Long customerId, Long restaurantId, String address, OrderStatus status, LocalDateTime creationDate, LocalDateTime doneDate, double amount) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.address = address;
        this.status = status;
        this.creationDate = creationDate;
        this.doneDate = doneDate;
        this.amount = amount;
    }

    public void addOrderItemToOrder(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
