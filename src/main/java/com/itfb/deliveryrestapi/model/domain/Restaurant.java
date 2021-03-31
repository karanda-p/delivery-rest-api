package com.itfb.deliveryrestapi.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }
}
