package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.itfb.fooddeliveryservice.model.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, CustomerDTO> {

    @Override
    @Mapping(source = "domain.cartId", target = "cartId")
    CustomerDTO domainToDto(Customer domain);
}
