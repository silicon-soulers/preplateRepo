package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.OrderItem;
import com.codewithyin.preplate.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findByOrderId(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public Optional<OrderItem> findbyId(Integer orderId) {
        return orderItemRepository.findById(orderId);
    }

    public void delete(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }
}
