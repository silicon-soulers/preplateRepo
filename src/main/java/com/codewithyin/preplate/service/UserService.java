package com.codewithyin.preplate.service;

import com.codewithyin.preplate.model.User;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Fetch user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save or update user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Delete user by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }
}
