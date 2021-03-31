package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
