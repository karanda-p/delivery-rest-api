package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Collection<CartItem> findAllByCartId(Long cartId);

    void deleteAllByCartId(Long cartId);

}
