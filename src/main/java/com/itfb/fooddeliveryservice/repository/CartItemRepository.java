package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Collection<CartItem> findAllByCartId(Long cartId);

    @Transactional
    void deleteAllByCartId(Long cartId);

}
