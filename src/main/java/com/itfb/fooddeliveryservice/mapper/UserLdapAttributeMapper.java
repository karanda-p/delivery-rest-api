package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class UserLdapAttributeMapper implements AttributesMapper<Customer> {

    @Override
    public Customer mapFromAttributes(Attributes attributes) throws NamingException {
        Customer customer = new Customer();

        Attribute attribute = attributes.get("sAMAccountName");
        if (attribute != null) {
            customer.setLogin((String) attribute.get());
        }

        return customer;
    }
}
