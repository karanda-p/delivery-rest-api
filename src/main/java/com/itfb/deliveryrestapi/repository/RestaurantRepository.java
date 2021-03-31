package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
