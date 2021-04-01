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
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CustomerService customerService;


    @GetMapping("/{customersLogin}/cart/items")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(@PathVariable String customersLogin) {
        return cartItemMapper.domainsToDtos(customerService
                .getCustomerByLogin(customersLogin)
                .get()
                .getCart()
                .getCartItems());
    }

    @PostMapping("/{customersLogin}/cart/items")
    public CartItemDTO addProductToCart(@RequestBody Product product, @PathVariable String customersLogin) {
        Customer customer = customerService.getCustomerByLogin(customersLogin).get();
        if (customer.getCart() == null) {
            Cart cart = new Cart();
            customer.setCart(cart);
            cartService.saveOrUpdateCart(cart);
        }
        CartItem cartItem = new CartItem(product, 1);
        customer.getCart().addCartItemToCart(cartItem);
        cartItem.setCart(customer.getCart());
        customerService.saveOrUpdateCustomer(customer);
        cartItemService.saveOrUpdateCartItem(cartItem);
        return cartItemMapper.domainToDto(cartItem);
    }

    @DeleteMapping("/{customersLogin}/cart")
    public void deleteCartByLogin(@PathVariable String customersLogin) {
        customerService.getCustomerByLogin(customersLogin).get().setCart(null);
        cartService.deleteCartById(customerService.getCustomerByLogin(customersLogin).get().getCartId());
    }
}
