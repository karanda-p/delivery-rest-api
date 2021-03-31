package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
