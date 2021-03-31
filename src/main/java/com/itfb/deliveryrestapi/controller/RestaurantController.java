package com.itfb.deliveryrestapi.controller;

import com.itfb.deliveryrestapi.model.Restaurant;
import com.itfb.deliveryrestapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String getAllRestaurants() {
        return null;
    }

}
