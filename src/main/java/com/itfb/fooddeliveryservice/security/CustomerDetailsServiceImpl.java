package com.itfb.fooddeliveryservice.security;

import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new CustomerDetailsImpl(customerRepository.findCustomerByLogin(username).get());
    }
}
