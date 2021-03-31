package com.itfb.deliveryrestapi.model.cart;

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

    @Column(name = "customer_id")
    private long customerId;

    @OneToMany(mappedBy = "cartId")
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(long customerId) {
        this.customerId = customerId;
    }

    public void addCartItemToCart(CartItem cartItem){
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
    }
}
