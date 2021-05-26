package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.exception.EntityNotFoundException;
import com.itfb.fooddeliveryservice.model.Message;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Collection<Product> getAllProductsByRestaurant(Long restaurantId) {
        if (!productRepository.findAllByRestaurantId(restaurantId).isEmpty()){
            return productRepository.findAllByRestaurantId(restaurantId);
        } else {
            throw new EntityNotFoundException(Message.RESTAURANT_NOT_FOUND, restaurantId);
        }
    }

    @Transactional
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Message.PRODUCT_NOT_FOUND, id));
    }

}
