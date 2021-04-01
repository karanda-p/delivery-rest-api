package com.itfb.fooddeliveryservice.model.domain.order;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
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
    @SequenceGenerator(sequenceName = "order_seq", name = "order_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_gen")
    private long id;

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
    private String creationDate;

    @Column(name = "done_date")
    private String doneDate;

    @Column(name = "amount")
    private double amount;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(Long customerId, Long restaurantId, String address, OrderStatus status, String creationDate, String doneDate, double amount) {
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
