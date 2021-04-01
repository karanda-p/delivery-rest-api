package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import com.itfb.fooddeliveryservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }
}
