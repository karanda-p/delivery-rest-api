package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.cart.Cart;
import com.itfb.fooddeliveryservice.model.dto.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends BaseMapper<Cart, CartDTO> {
}
