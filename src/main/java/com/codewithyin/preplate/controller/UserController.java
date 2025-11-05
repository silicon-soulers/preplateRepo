package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.AppUser;
import com.codewithyin.preplate.model.MenuItem;
import com.codewithyin.preplate.model.Order;
import com.codewithyin.preplate.service.MenuItemService;
import com.codewithyin.preplate.service.OrderService;
import com.codewithyin.preplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final MenuItemService menuItemService;

    public UserController (UserService userService, MenuItemService menuItemService) {
        this.userService = userService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Model model,  Principal principal) {
        AppUser user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() ->new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "user/user_dashboard";
    }

    @GetMapping("/user/user_profile")
    public String userProfile(Model model, Principal principal) {
        AppUser user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() ->new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "user/user_profile";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model, Principal principal) {
        List<MenuItem> menu_list = menuItemService.findByMenuItemName(query);
        model.addAttribute("menu_list", menu_list);
        return "user/user_dashboard";
    }

}