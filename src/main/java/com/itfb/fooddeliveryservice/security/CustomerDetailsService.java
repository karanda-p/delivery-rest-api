package com.itfb.fooddeliveryservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerDetailsService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

}
