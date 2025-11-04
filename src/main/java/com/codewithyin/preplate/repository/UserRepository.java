package com.codewithyin.preplate.repository;

import com.codewithyin.preplate.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByName(String username);
    Optional<AppUser> findByEmail(String email);
}