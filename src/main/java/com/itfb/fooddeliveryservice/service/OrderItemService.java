package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItem saveOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
