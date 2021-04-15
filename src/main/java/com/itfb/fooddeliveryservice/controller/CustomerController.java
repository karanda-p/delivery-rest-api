package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.CustomerMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        return customerMapper.domainToDto(customerService.saveCustomer(customer));
    }

    @GetMapping
    public CustomerDTO getCustomerByLogin(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletResponse resp) {
        return customerMapper.domainToDto(customerService.getCustomerByLogin(userDetails.getLogin()));
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody Customer customer) {
        return customerMapper.domainToDto(customerService.updateCustomer(customer));
    }
}
