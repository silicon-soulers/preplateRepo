package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.Restaurant;
import com.codewithyin.preplate.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }


    public Optional<Restaurant> getRestaurantByEmail(String email) {
        return restaurantRepository.findByEmail(email);
    }


    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public List<Restaurant> search(String query) {
        return restaurantRepository.findByNameContainingIgnoreCase(query);
    }
}