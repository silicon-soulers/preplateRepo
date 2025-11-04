package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.Restaurant;
import com.codewithyin.preplate.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }


    public Restaurant get(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }


    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public List<Restaurant> search(String query) {
        return restaurantRepository.findByNameContainingIgnoreCase(query);
    }
}