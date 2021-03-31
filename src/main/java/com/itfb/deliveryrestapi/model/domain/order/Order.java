package com.itfb.deliveryrestapi.model.domain.order;

import com.itfb.deliveryrestapi.model.domain.Customer;
import com.itfb.deliveryrestapi.model.domain.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
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

    public Order() {
    }

    public Order(Customer customerId, Restaurant restaurantId, String address, OrderStatus status, String creationDate, String doneDate, double amount) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.address = address;
        this.status = status;
        this.creationDate = creationDate;
        this.doneDate = doneDate;
        this.amount = amount;
    }

    public void addOrderItemToOrder(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
    }
}
