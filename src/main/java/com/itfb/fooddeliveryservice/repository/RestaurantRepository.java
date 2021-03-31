package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
