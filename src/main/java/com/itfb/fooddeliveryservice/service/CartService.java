package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final CartItemService cartItemService;

    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }

    public void deleteCartById(Long id){
        cartRepository.deleteById(id);
    }

    public Cart saveOrUpdateCart(Cart cart){
        return cartRepository.save(cart);
    }

    public CartItem addProductToCart(Product product, Long customerId){
        Customer customer = customerService.getCustomerById(customerId).get();
        if (customer.getCart() == null) {
            Cart cart = new Cart();
            customer.setCart(cart);
            customerService.saveOrUpdateCustomer(customer);
        }
        CartItem cartItem = new CartItem(product, 1);
        for (CartItem item : customer.getCart().getCartItems()) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return cartItemService.saveOrUpdateCartItem(item);
            }
        }
        customer.getCart().addCartItemToCart(cartItem);
        cartItem.setCart(customer.getCart());
        return cartItemService.saveOrUpdateCartItem(cartItem);
    }
}
