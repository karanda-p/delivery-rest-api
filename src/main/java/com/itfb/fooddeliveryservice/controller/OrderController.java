package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping("/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(userDetails.getId()));
    }

    @GetMapping("/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderMapper.domainToDto(orderService.getOrderById(orderId));
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    OrderDTO createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody Order order) throws IOException {
        return orderMapper.domainToDto(orderService.createNewOrder(userDetails.getUsername(), order));
    }

    //Change order status
    @PutMapping("/orders")
    public OrderDTO changeOrderStatus(@RequestBody Order order) {
        return orderMapper.domainToDto(orderService.changeOrderStatus(order));
    }
}
