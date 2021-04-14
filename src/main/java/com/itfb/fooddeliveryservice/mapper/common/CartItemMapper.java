package com.itfb.fooddeliveryservice.mapper.common;

import com.itfb.fooddeliveryservice.mapper.BaseMapper;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.dto.CartItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends BaseMapper<CartItem, CartItemDTO> {

    @Override
    @Mappings({
            @Mapping(source = "domain.productId", target = "productId"),
            @Mapping(source = "domain.cartId", target = "cartId")
    })
    CartItemDTO domainToDto(CartItem domain);
}
