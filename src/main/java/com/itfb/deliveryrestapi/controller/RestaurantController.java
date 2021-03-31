package com.itfb.deliveryrestapi.controller;

import com.itfb.deliveryrestapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @GetMapping
    public String getAllRestaurants() {
        return null;
    }

}
