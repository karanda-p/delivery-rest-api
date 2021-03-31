package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
