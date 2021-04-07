package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerRepository customerRepository;

    public Customer saveOrUpdateCustomer(Customer customer){
        Customer newCustomer = customer;
        newCustomer.setEnabled(true);
        newCustomer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerByLogin(String login){
        return customerRepository.findCustomerByLogin(login);
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }
}
