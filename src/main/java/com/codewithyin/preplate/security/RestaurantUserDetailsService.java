package com.codewithyin.preplate.security;

import com.codewithyin.preplate.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantUserDetailsService implements UserDetailsService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Loading restaurant user by email: {}", email);

        var restaurant = restaurantRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Restaurant not found with email: {}", email);
                    return new UsernameNotFoundException("Restaurant not found with email: " + email);
                });

        log.debug("Restaurant found: {}", restaurant.getEmail());

        return org.springframework.security.core.userdetails.User
                .withUsername(restaurant.getEmail())
                .password(restaurant.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_RESTAURANT")))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false) // Set based on your entity if you have this field
                .build();
    }
}