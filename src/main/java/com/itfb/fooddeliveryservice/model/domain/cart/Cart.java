package com.itfb.fooddeliveryservice.model.domain.cart;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @SequenceGenerator(sequenceName = "cart_seq", name = "cart_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_gen")
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Cart() {
        this.creationDate = LocalDateTime.now();
    }

    public void addCartItemToCart(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }
}
