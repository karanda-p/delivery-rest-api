package com.itfb.fooddeliveryservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant {

    @Id
    @SequenceGenerator(sequenceName = "restaurant_seq", name = "restaurant_id_gen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id_gen")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    public Restaurant() {
    }

    public Restaurant(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
