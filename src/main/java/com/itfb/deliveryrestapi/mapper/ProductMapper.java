package com.itfb.deliveryrestapi.mapper;

import com.itfb.deliveryrestapi.model.domain.Product;
import com.itfb.deliveryrestapi.model.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDTO>{
}
