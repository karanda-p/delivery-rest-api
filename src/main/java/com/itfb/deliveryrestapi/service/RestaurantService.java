package com.itfb.deliveryrestapi.service;

import com.itfb.deliveryrestapi.model.Restaurant;
import com.itfb.deliveryrestapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Collection<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant getById(Long id){
        return restaurantRepository.findById(id).get();
    }
}
