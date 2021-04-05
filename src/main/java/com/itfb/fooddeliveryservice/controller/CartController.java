package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.dto.CartItemDTO;
import com.itfb.fooddeliveryservice.service.CartItemService;
import com.itfb.fooddeliveryservice.service.CartService;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{customersId}/cart/items")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(@PathVariable Long customersId) {
        return cartItemMapper.domainsToDtos(customerService
                .getCustomerById(customersId)
                .get()
                .getCart()
                .getCartItems());
    }

    @PostMapping("/{customerId}/cart/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDTO addProductToCart(@RequestBody Product product, @PathVariable Long customerId) {
        return cartItemMapper.domainToDto(cartService.addProductToCart(product, customerId));
    }

    @DeleteMapping("/{customerId}/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartByLogin(@PathVariable Long customerId) {
        customerService.getCustomerById(customerId).get().setCart(null);
        cartService.deleteCartById(customerService.getCustomerById(customerId).get().getCartId());
    }

    @DeleteMapping("/{customerId}/cart/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItemFromCart(@PathVariable Long customerId, @RequestBody CartItem cartItem) {
        cartItemService.deleteCartItemFromCart(customerId, cartItem);
    }
}
