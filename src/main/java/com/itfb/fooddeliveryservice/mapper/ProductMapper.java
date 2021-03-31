package com.itfb.fooddeliveryservice.mapper;

import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    ProductDTO domainToDTO(Product domain);
}
