package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.CustomerMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.security.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public CustomerDTO addCustomer(@RequestBody Customer customer) {
        return customerMapper.domainToDto(customerService.saveOrUpdateCustomer(customer));
    }

    @GetMapping
    public CustomerDTO getCustomerByLogin(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletResponse resp) {
        return customerMapper.domainToDto(customerService.getCustomerByLogin(userDetails.getLogin()));
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody Customer customer) {
        return customerMapper.domainToDto(customerService.saveOrUpdateCustomer(customer));
    }
}
