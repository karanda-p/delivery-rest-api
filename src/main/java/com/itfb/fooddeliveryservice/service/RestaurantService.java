package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.exception.EntityNotFoundException;
import com.itfb.fooddeliveryservice.model.Message;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Collection<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Message.RESTAURANT_NOT_FOUND, id));
    }
}
