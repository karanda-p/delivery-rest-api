package com.itfb.deliveryrestapi.controller;

import com.itfb.deliveryrestapi.model.Restaurant;
import com.itfb.deliveryrestapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public Collection<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){
        return restaurantService.getById(id);
    }
}
