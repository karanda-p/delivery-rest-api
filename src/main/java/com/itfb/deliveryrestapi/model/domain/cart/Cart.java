package com.itfb.deliveryrestapi.model.domain.cart;

import com.itfb.deliveryrestapi.model.domain.Customer;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cartId")
    private List<CartItem> cartItems;

    public Cart() {
    }

    public void addCartItemToCart(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
    }
}
