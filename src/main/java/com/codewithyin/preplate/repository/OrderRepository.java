package com.codewithyin.preplate.repository;

import com.codewithyin.preplate.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByAppUserIdOrderByCreatedAtDesc(Long userId);
    List<Order> findByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);
}