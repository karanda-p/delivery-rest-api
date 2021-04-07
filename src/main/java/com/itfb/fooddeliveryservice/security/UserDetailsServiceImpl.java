package com.itfb.fooddeliveryservice.security;

import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if(customerRepository.findCustomerByLogin(s).isPresent()){
            return new UserDetailsImpl(customerRepository.findCustomerByLogin(s).get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
