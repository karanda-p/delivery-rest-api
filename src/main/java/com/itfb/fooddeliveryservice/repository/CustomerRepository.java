package com.itfb.fooddeliveryservice.repository;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByLogin(String login);
}
