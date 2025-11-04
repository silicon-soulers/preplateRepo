package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.AppUser;
import com.codewithyin.preplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserAuthController {

        private final UserService userService;
        private final PasswordEncoder passwordEncoder;

        @GetMapping("/user/login")
        public String userLoginPage() {
            return "auth/user-login";
        }

        @GetMapping("/user/register")
        public String registerPage(Model model) {
            model.addAttribute("user", new AppUser());
            return "auth/user-register";
        }

        @PostMapping("/user/register")
        public String registerUser(@ModelAttribute AppUser appUser) {
            String encoded = passwordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encoded);
            userService.registerUser(appUser);
            return "redirect:/user/login?success";
        }

    }
