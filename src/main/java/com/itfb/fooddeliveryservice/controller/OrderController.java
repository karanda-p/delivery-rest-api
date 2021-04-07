package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CartItemToOrderItemMapper;
import com.itfb.fooddeliveryservice.mapper.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderStatus;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.security.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(userDetails.getId()));
    }

    @GetMapping("/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderMapper.domainToDto(orderService.getOrderById(orderId).get());
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    OrderDTO createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody Order order) {
        return orderMapper.domainToDto(orderService.createNewOrder(userDetails.getUsername(), order));
    }

    //Change order status
    @PutMapping("/orders")
    public OrderDTO changeOrderStatus(@RequestBody Order order) {
        return orderMapper.domainToDto(orderService.changeOrderStatus(order));
    }
}
