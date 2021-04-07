package com.itfb.fooddeliveryservice.security;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findCustomerByLogin(s);
        if (customer.isPresent()) {
            return UserDetailsImpl.builder()
                    .login(customer.get().getLogin())
                    .password(customer.get().getPassword())
                    .enabled(customer.get().isEnabled())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
