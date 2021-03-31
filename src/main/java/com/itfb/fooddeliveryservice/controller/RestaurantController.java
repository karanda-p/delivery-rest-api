package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.ProductMapper;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.model.dto.ProductDTO;
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

    @GetMapping
    public Collection<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){
        return restaurantService.getById(id);
    }

    @GetMapping("/{id}/products")
    public Collection<ProductDTO> getAllRestaurantProducts(@PathVariable Long id){
        Restaurant restaurant = restaurantService.getById(id);
        return productMapper.domainsToDtos(productService.getAllProductsByRestaurant(restaurant));
//        return productService.getAllProductsByRestaurant(restaurant);
    }
}
