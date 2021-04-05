package com.itfb.fooddeliveryservice.model.domain.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @SequenceGenerator(sequenceName = "cart_seq", name = "cart_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_gen")
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @Column(name = "creation_date")
    private String creationDate;

    public Cart() {
        this.creationDate = LocalDate.now().toString();
    }

    public void addCartItemToCart(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
    }
}
