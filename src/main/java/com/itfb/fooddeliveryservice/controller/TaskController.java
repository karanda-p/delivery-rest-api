package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

//    @PostMapping("/task")
//    public OrderDTO updateOrderStatus(@RequestBody Task task){
//        return orderMapper.domainToDto(orderService.updateOrderStatus(task));
//    }
}
