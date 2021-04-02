package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {

    @Mapping(source = "domain.status.value", target = "status")
    OrderDTO domainToDto(Order domain);
}
