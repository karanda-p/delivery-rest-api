package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
