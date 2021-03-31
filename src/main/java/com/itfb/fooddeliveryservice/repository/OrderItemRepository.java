package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
