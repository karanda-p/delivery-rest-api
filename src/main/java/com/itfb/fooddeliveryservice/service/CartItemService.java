package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CustomerService customerService;

    public Collection<CartItem> findAllCartItemsByCartId(Long cartId){
        return cartItemRepository.findAllByCartId(cartId);
    }

    public CartItem saveOrUpdateCartItem(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void deleteAllCartItemsByCartId(Long cartId){
        cartItemRepository.deleteAllByCartId(cartId);
    }

    public void deleteCartItemFromCart(String customerLogin, CartItem cartItem){
        Customer customer = customerService.getCustomerByLogin(customerLogin).get();
        customer.getCart().getCartItems().remove(cartItem);
        customerService.saveOrUpdateCustomer(customer);
    }
}
