package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.OrderMapper;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.service.CustomerService;
import com.itfb.fooddeliveryservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/{customerId}/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@PathVariable Long customerId){
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(customerId));
    }

    @GetMapping("/{customerId}/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId){
        return orderMapper.domainToDto(orderService.getOrderById(orderId).get());
    }
}
