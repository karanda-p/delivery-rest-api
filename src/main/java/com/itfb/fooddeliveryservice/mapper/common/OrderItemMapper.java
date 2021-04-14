package com.itfb.fooddeliveryservice.mapper.common;

import com.itfb.fooddeliveryservice.mapper.BaseMapper;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import com.itfb.fooddeliveryservice.model.dto.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemDTO> {
}
