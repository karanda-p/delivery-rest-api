package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CustomerMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return customerMapper.domainToDto(customer);
    }

    @GetMapping("/{login}")
    public CustomerDTO getCustomerByLogin(@PathVariable String login){

        return customerMapper.domainToDto(customerService.getCustomerByLogin(login).get());
    }
}
