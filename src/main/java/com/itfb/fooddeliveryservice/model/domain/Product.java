package com.itfb.fooddeliveryservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(sequenceName = "product_seq", name = "product_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private Long restaurantId;

    @Column(name = "price")
    private double price;

    @Column(name = "name")
    private String name;

    public Product() {
    }

    public Product(Long restaurantId, double price, String name) {
        this.restaurantId = restaurantId;
        this.price = price;
        this.name = name;
    }
}
