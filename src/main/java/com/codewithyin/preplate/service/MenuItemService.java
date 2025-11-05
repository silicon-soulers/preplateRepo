package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.MenuItem;
import com.codewithyin.preplate.model.Restaurant;
import com.codewithyin.preplate.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long  restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public List<MenuItem> findByMenuItemName(String menuItemName) {
        return menuItemRepository.findMenuItemByNameContainingIgnoreCase(menuItemName);
    }

    public Optional<MenuItem> findById(Long id) {
        return menuItemRepository.findById(id);
    }

    public void  delete(MenuItem menuItem) {
        menuItemRepository.delete(menuItem);
    }
}
