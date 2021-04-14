package com.itfb.fooddeliveryservice.mapper.common;

import com.itfb.fooddeliveryservice.mapper.BaseMapper;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.model.dto.RestaurantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantDTO> {

}
