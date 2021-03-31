package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
