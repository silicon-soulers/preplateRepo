package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.AppUser;
import com.codewithyin.preplate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection is the best practice
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch all users
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch user by id
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Fetch user by username
    public Optional<AppUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save or update user
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    // Delete user by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void registerUser(AppUser appUser) {
        userRepository.save(appUser);
    }
}
