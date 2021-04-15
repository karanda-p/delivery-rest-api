package com.itfb.fooddeliveryservice.security.Impl;

import com.itfb.fooddeliveryservice.exception.OperationNotAllowedException;
import com.itfb.fooddeliveryservice.mapper.UserLdapAttributeMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import com.itfb.fooddeliveryservice.security.UserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.util.Collection;

@Component("ldapUserDetailsContextMapper")
@RequiredArgsConstructor
public class LdapUserDetailsContextMapperImpl implements UserDetailsContextMapper {

    private final CustomerRepository customerRepository;
    private final UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations userData, String username, Collection<? extends GrantedAuthority> collection) {

        Customer customer = null;
        try {
            customer = new UserLdapAttributeMapper().mapFromAttributes(userData.getAttributes());
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());
        }

        if (customerRepository.findCustomerByLogin(username).isPresent()) {
            customer = customerRepository.findCustomerByLogin(username).get();
        } else {
            customer.setEnabled(true);
//            customer.setPassword("");
            customer = customerRepository.save(customer);
        }

        return userDetailsMapper.domainToImpl(customer);
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {
        throw new OperationNotAllowedException();
    }
}
