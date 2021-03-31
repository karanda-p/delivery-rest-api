package com.itfb.deliveryrestapi.model.domain.cart;

import com.itfb.deliveryrestapi.model.domain.Product;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cartId;
}
