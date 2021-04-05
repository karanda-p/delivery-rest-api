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

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CustomerService customerService;

    @GetMapping("/{customersId}/cart/items")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(@PathVariable Long customersId) {
        return cartItemMapper.domainsToDtos(customerService
                .getCustomerById(customersId)
                .get()
                .getCart()
                .getCartItems());
    }

    @PostMapping("/{customersId}/cart/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDTO addProductToCart(@RequestBody Product product, @PathVariable Long customersId) {
        Customer customer = customerService.getCustomerById(customersId).get();
        if (customer.getCart() == null) {
            Cart cart = new Cart();
            customer.setCart(cart);
            customerService.saveOrUpdateCustomer(customer);
        }
        CartItem cartItem = new CartItem(product, 1);
        for (CartItem item : customer.getCart().getCartItems()) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + 1);
                CartItem savedItem = cartItemService.saveOrUpdateCartItem(item);
                return cartItemMapper.domainToDto(savedItem);
            }
        }

        customer.getCart().addCartItemToCart(cartItem);
        cartItem.setCart(customer.getCart());
        CartItem savedCartItem = cartItemService.saveOrUpdateCartItem(cartItem);
        return cartItemMapper.domainToDto(savedCartItem);
    }

    @DeleteMapping("/{customersId}/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartByLogin(@PathVariable Long customersId) {
        customerService.getCustomerById(customersId).get().setCart(null);
        cartService.deleteCartById(customerService.getCustomerById(customersId).get().getCartId());
    }

    @DeleteMapping("/{customersId}/cart/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItemFromCart(@PathVariable Long customersId, @RequestBody CartItem cartItem) {
        Customer customer = customerService.getCustomerById(customersId).get();
        customer.getCart().getCartItems().remove(cartItem);
        customerService.saveOrUpdateCustomer(customer);
    }
}
