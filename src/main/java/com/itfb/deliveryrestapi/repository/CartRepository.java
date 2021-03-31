package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
