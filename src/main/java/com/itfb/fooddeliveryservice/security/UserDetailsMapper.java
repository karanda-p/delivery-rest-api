package com.itfb.fooddeliveryservice.security;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.domain.Role;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsMapper {

    public UserDetailsImpl domainToImpl(Customer customer) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(Role.USER));

        return new UserDetailsImpl(
                customer.getId(),
                customer.getLogin(),
                customer.getPassword(),
                customer.isEnabled(),
                authorities
        );
    }
}
