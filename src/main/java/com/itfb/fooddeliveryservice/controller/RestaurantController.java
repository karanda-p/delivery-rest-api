package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.ProductMapper;
import com.itfb.fooddeliveryservice.mapper.RestaurantMapper;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.model.dto.ProductDTO;
import com.itfb.fooddeliveryservice.model.dto.RestaurantDTO;
import com.itfb.fooddeliveryservice.service.ProductService;
import com.itfb.fooddeliveryservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public Collection<RestaurantDTO> getAllRestaurants() {
        return restaurantMapper.domainsToDtos(restaurantService.getAll());
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantMapper.domainToDto(restaurantService.getById(id).get());
    }

    @GetMapping("/{id}/products")
    public Collection<ProductDTO> getAllRestaurantProducts(@PathVariable Long id) {
        return productMapper.domainsToDtos(productService.getAllProductsByRestaurant(id));
    }

    @GetMapping("/{id}/products/{productId}")
    public ProductDTO getProduct(@PathVariable Long productId) {
        return productMapper.domainToDto(productService.getProductById(productId).get());
    }

}
