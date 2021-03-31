package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
