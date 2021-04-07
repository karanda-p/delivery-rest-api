package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemMapper;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.dto.CartItemDTO;
import com.itfb.fooddeliveryservice.security.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.CartItemService;
import com.itfb.fooddeliveryservice.service.CartService;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CartController {

    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CustomerService customerService;
    private final CartItemService cartItemService;

    @GetMapping("/cart/items")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartItemMapper.domainsToDtos(customerService
                .getCustomerByLogin(userDetails.getLogin())
                .get()
                .getCart()
                .getCartItems());
    }

    @PostMapping("/cart/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDTO addProductToCart(@RequestBody Product product
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartItemMapper.domainToDto(cartService.addProductToCart(product, userDetails.getLogin()));
    }

    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartByLogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        customerService.getCustomerByLogin(userDetails.getLogin()).get().setCart(null);
        cartService.deleteCartById(customerService.getCustomerByLogin(userDetails.getLogin()).get().getCartId());
    }

    @DeleteMapping("/cart/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItemFromCart(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @RequestBody CartItem cartItem) {
        cartItemService.deleteCartItemFromCart(userDetails.getLogin(), cartItem);
    }
}
