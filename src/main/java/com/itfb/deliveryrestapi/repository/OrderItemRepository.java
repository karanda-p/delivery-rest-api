package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
