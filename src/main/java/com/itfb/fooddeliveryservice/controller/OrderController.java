package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.mapper.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.service.CartItemService;
import com.itfb.fooddeliveryservice.service.CustomerService;
import com.itfb.fooddeliveryservice.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    private final CartItemToOrderItemMapper cartItemToOrderItemMapper;

    @GetMapping("/{customerId}/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@PathVariable Long customerId){
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(customerId));
    }

    @GetMapping("/{customerId}/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId){
        return orderMapper.domainToDto(orderService.getOrderById(orderId).get());
    }

    @PostMapping("/{customerId}/orders")
    public OrderDTO createOrder(@PathVariable Long customerId, @RequestBody Order order){
        Customer customer = customerService.getCustomerById(customerId).get();
        order.setCustomer(customer);
        for (CartItem cartItem: customer.getCart().getCartItems()) {
            order.addOrderItemToOrder(cartItemToOrderItemMapper.cartItemToOrderItem(cartItem));
        }
        for (OrderItem orderItem: order.getOrderItems()){
            orderItem.setOrder(order);
        }
        order.setRestaurant(order.getOrderItems()
                .get(0)
                .getProduct()
                .getRestaurant());

        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setCreationDate(LocalDate.now().toString());
        return null;
    }
}
