package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.exception.EntityNotFoundException;
import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.model.Message;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CartItemToOrderItemMapper cartItemToOrderItemMapper;

    @Transactional
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Message.ENTITY_NOT_FOUND, id));
    }

    @Transactional
    public Collection<Order> getAllOrdersByCustomerId(Long customerId) {
        return orderRepository.getAllByCustomerId(customerId);
    }

    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order createNewOrder(String login, Order order) {
        Customer customer = customerService.getCustomerByLogin(login).orElseThrow(() -> new EntityNotFoundException(Message.ENTITY_NOT_FOUND, login));
        if (customer.getCart() == null)
            throw new EntityNotFoundException(Message.CART_IS_EMPTY, login);
        order.setCustomer(customer);
        double amount = 0D;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            order.addOrderItemToOrder(cartItemToOrderItemMapper.cartItemToOrderItem(cartItem));
            amount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        order.setAmount(amount);
        order.setRestaurant(order.getOrderItems()
                .get(0)
                .getProduct()
                .getRestaurant());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setCreationDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        customer.setCart(null);
        cartService.deleteCartById(customer.getCartId());
        customerService.saveOrUpdateCustomer(customer);
        return savedOrder;
    }

    @Transactional
    public Order changeOrderStatus(Order order) {
        Order newOrder = orderRepository.getOne(order.getId());
        newOrder.setStatus(order.getStatus());
        return orderRepository.save(newOrder);
    }
}
