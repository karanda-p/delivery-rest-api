package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.Restaurant;
import com.itfb.fooddeliveryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Collection<Product> getAllProductsByRestaurant(Long restaurantId){
        return productRepository.findAllByRestaurantId(restaurantId);
    }

}
