package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.Role;
import com.codewithyin.preplate.model.User;
import com.codewithyin.preplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("auth/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("auth/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("auth/register")
    public String register(@ModelAttribute User user, @RequestParam String roleType) {
        if (roleType.equals("owner")) {
            user.setRole(Role.OWNER);
        } else {
            user.setRole(Role.USER);
        }
        userService.registerUser(user);
        return "redirect:/auth/login?success";
    }
}