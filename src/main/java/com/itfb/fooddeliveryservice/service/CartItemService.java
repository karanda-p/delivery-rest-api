package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public Collection<CartItem> findAllCartItemsByCartId(Long cartId){
        return cartItemRepository.findAllByCartId(cartId);
    }

    public void saveOrUpdateCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }
}
