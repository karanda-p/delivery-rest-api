package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CartItemToOrderItemMapper cartItemToOrderItemMapper;

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Collection<Order> getAllOrdersByCustomerId(Long customerId){
        return orderRepository.getAllByCustomerId(customerId);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Order createNewOrder(String login, Order order){
        Customer customer = customerService.getCustomerByLogin(login).get();
        order.setCustomer(customer);
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            order.addOrderItemToOrder(cartItemToOrderItemMapper.cartItemToOrderItem(cartItem));
        }
        double amount = 0D;
        for (OrderItem orderItem : order.getOrderItems()) {
            amount += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        order.setAmount(amount);
        order.setRestaurant(order.getOrderItems()
                .get(0)
                .getProduct()
                .getRestaurant());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setCreationDate(LocalDate.now().toString());
        Order savedOrder = orderRepository.save(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }

        customer.setCart(null);
        cartService.deleteCartById(customer.getCartId());
        customerService.saveOrUpdateCustomer(customer);
        return savedOrder;
    }

    public Order changeOrderStatus(Order order){
        Order newOrder = orderRepository.getOne(order.getId());
        newOrder.setStatus(order.getStatus());
        Order savedOrder = orderRepository.save(newOrder);
        return savedOrder;
    }
}
