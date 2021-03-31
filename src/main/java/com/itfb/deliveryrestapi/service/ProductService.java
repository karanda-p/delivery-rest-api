package com.itfb.deliveryrestapi.service;

import com.itfb.deliveryrestapi.model.domain.Product;
import com.itfb.deliveryrestapi.model.domain.Restaurant;
import com.itfb.deliveryrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Collection<Product> getAllProductsByRestaurant(Restaurant restaurant){
        return productRepository.getAllByRestaurantId(restaurant);
    }
}
