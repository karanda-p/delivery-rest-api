package com.itfb.fooddeliveryservice.mapper.common;

import com.itfb.fooddeliveryservice.mapper.BaseMapper;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {
}
