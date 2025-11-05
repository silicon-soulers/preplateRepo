package com.codewithyin.preplate.config;

import com.codewithyin.preplate.security.RestaurantUserDetailsService;
import com.codewithyin.preplate.security.UserUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserUserDetailsService userDetailsService;
    private final RestaurantUserDetailsService restaurantDetailsService;

    @Bean
    @Order(1)
    public SecurityFilterChain restaurantSecurity(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(restaurantDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        http
                .securityMatcher("/restaurant/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/restaurant/login", "/restaurant/register",
                                "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/restaurant/login")
                        .loginProcessingUrl("/restaurant/login")
                        .defaultSuccessUrl("/restaurant/dashboard", true)
                        .failureUrl("/restaurant/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/")
                        .logoutSuccessUrl("/restaurant/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain userSecurity(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        http
                .securityMatcher("/user/**", "/", "/css/**", "/js/**", "/images/**")
                .authenticationProvider(provider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/user/login", "/user/register",
                                "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/user/dashboard", true)
                        .failureUrl("/user/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}