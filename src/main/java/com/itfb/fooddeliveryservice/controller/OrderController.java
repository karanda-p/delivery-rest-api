package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.mapper.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CartItemService cartItemService;
    private final CartService cartService;
    private final CartItemToOrderItemMapper cartItemToOrderItemMapper;
    private final OrderItemService orderItemService;

    @GetMapping("/{customerId}/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@PathVariable Long customerId) {
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(customerId));
    }

    @GetMapping("/{customerId}/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderMapper.domainToDto(orderService.getOrderById(orderId).get());
    }

    @PostMapping("/{customerId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody OrderDTO createOrder(@PathVariable Long customerId
            , @RequestBody Order order) {
        Customer customer = customerService.getCustomerById(customerId).get();
        order.setCustomer(customer);
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            order.addOrderItemToOrder(cartItemToOrderItemMapper.cartItemToOrderItem(cartItem));
        }
        double amount = 0D;
        for (OrderItem orderItem : order.getOrderItems()) {
//            orderItem.setOrder(order);
//            orderItemService.saveOrderItem(orderItem);
            amount += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        order.setAmount(amount);
        order.setRestaurant(order.getOrderItems()
                .get(0)
                .getProduct()
                .getRestaurant());

        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setCreationDate(LocalDate.now().toString());
        Order savedOrder = orderService.saveOrder(order);
        for (OrderItem orderItem: order.getOrderItems()){
            orderItem.setOrder(order);
        }
        customer.getCart().setCartItems(null);
        cartItemService.deleteAllCartItemsByCartId(customer.getCartId());
        customer.setCart(null);
        cartService.deleteCartById(customer.getCartId());

        customerService.saveOrUpdateCustomer(customer);
        return orderMapper.domainToDto(savedOrder);
    }
}
