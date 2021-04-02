package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Collection<Order> getAllOrdersByCustomerId(Long customerId){
        return orderRepository.getAllByCustomerId(customerId);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
