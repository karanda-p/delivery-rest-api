package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.domain.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CartItemToOrderItemMapper {

    @Mappings({
            @Mapping(source = "productId", target = "productId"),
            @Mapping(target = "id", ignore = true)
    })
    OrderItem cartItemToOrderItem(CartItem cartItem);

    Collection<OrderItem> cartItemsToOrderItems(Collection<CartItem> cartItems);
}
