package com.itfb.deliveryrestapi.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "price")
    private double price;

    @Column(name = "name")
    private String name;

    public Product() {
    }

    public Product(Restaurant restaurantId, double price) {
        this.restaurantId = restaurantId;
        this.price = price;
    }
}
