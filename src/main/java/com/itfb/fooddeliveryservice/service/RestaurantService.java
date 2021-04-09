package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Collection<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }
}
