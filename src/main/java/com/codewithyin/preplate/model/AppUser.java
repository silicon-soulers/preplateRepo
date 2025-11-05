package com.codewithyin.preplate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "app_user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String city;

    private String profileImage;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Order> orders;
}