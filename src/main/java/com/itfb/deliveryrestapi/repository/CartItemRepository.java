package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.domain.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
