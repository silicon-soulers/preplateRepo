package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.Order;
import com.codewithyin.preplate.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final OrderRepository orderRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/restaurant/dashboard")
    public String dashboard(Model model) {
        Long restaurantId = 1L; // placeholder; derive from logged-in owner
        List<Order> orders = orderRepository.findByRestaurantIdOrderByCreatedAtDesc(restaurantId);
        model.addAttribute("orders", orders);
        return "restaurant/dashboard";
    }

    @PostMapping("/restaurant/order/{id}/accept")
    public String accept(@PathVariable Long id) {
        Order o = orderRepository.findById(id).orElseThrow();
        o.setStatus("ACCEPTED");
        orderRepository.save(o);
        messagingTemplate.convertAndSend("/topic/order-status/" + o.getUserId(), o);
        return "redirect:/restaurant/dashboard";
    }
}