package com.itfb.fooddeliveryservice.model.domain.cart;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany
    private List<CartItem> cartItems;

    @Column(name = "creation_date")
    private String creationDate;

    public Cart() {
    }

    public void addCartItemToCart(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
    }
}
