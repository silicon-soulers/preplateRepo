package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.Order;
import com.codewithyin.preplate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final OrderService orderService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Authentication auth, Model model) {
        // Fetch user's orders
        // Assume UserService can find user by username
        Long userId = 1L; // In real app: fetch from auth principal
        List<Order> orders = orderService.getOrdersForUser(userId);
        model.addAttribute("orders", orders);
        return "user/dashboard";
    }
}