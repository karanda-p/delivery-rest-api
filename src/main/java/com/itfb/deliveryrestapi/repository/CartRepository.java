package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
