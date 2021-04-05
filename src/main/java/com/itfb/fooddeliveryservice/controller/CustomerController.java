package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CustomerMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveOrUpdateCustomer(customer);
        return customerMapper.domainToDto(savedCustomer);
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerByLogin(@PathVariable Long customerId, HttpServletResponse resp){
        if (customerService.getCustomerById(customerId).isPresent()){
            return customerMapper.domainToDto(customerService.getCustomerById(customerId).get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveOrUpdateCustomer(customer);
        return customerMapper.domainToDto(savedCustomer);
    }
}
