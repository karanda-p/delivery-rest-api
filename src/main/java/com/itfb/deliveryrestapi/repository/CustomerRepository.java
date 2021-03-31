package com.itfb.deliveryrestapi.repository;

import com.itfb.deliveryrestapi.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
