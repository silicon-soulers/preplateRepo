package com.codewithyin.preplate.controller;

import com.codewithyin.preplate.model.MenuItem;
import com.codewithyin.preplate.model.Order;
import com.codewithyin.preplate.model.Restaurant;
import com.codewithyin.preplate.repository.OrderRepository;
import com.codewithyin.preplate.repository.RestaurantRepository;
import com.codewithyin.preplate.service.MenuItemService;
import com.codewithyin.preplate.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    @GetMapping("/restaurant/dashboard")
    public String dashboard(Model model, Principal principal) {
        String email = principal.getName(); // or username
        Restaurant restaurant = restaurantService.getRestaurantByEmail(email).orElseThrow();
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurantId(restaurant.getId());

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("menuItem", new MenuItem()); // for the form
        model.addAttribute("menuItems", menuItems);     // for list display
        return "restaurant/restaurant_dashboard";

    }

    @PostMapping("/restaurant/menu/add")
    public String addMenuItem(@ModelAttribute("menuItem") MenuItem menuItem,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Principal principal) throws IOException {

        String email = principal.getName();
        Restaurant restaurant = restaurantService.getRestaurantByEmail(email).orElseThrow();


        // Save image to local folder
        String uploadDir = "menu-images/";
        String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName));

        // Save the image file path in DB
        menuItem.setImage("/" + uploadDir + fileName);

        menuItem.setRestaurant(restaurant); // link menu to this restaurant
        menuItemService.save(menuItem);// for list display

        return "redirect:/restaurant/dashboard";
    }
}