package com.codewithyin.preplate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @ManyToOne
    private Restaurant restaurant;

    private String status;
    private LocalDateTime createdAt;


    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;


// getters/setters
}