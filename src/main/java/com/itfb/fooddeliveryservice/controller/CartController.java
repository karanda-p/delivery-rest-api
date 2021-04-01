package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemMapper;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.dto.CartItemDTO;
import com.itfb.fooddeliveryservice.service.CartItemService;
import com.itfb.fooddeliveryservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;


    @GetMapping("/{id}/items/")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/items/")
    public CartItemDTO addProductToCart(@RequestBody Product product, @RequestParam Long id) {
        Cart cart;
        if (cartService.findCartById(id).isEmpty()) {
            cart = new Cart();
        } else {
            cart = cartService.findCartById(id).get();
        }
        cart.addCartItemToCart(new CartItem(product));

        return null;
    }
}
