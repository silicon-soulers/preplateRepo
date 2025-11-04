package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.Restaurant;
import com.codewithyin.preplate.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RestaurantAuthController {

    private final PasswordEncoder passwordEncoder;
    private final RestaurantService restaurantService;

    @GetMapping("/restaurant/login")
    public String loginPage() {
        return "auth/restaurant-login";
    }

    @GetMapping("/restaurant/register")
    public String restaurantRegister(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "auth/restaurant-register";
    }

    @PostMapping("/restaurant/register")
    public String registerOwner(@ModelAttribute Restaurant restaurant) {
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        restaurantService.save(restaurant);
        return "redirect:/restaurant/login?success";
    }
}
