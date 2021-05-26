package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findAllByRestaurantId(Long restaurantId);
}
