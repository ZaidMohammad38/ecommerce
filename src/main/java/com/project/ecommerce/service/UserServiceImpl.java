package com.project.ecommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entity.User;
import com.project.ecommerce.repository.AdminRepository;
import com.project.ecommerce.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final AdminRepository adminRepository;

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository, AdminRepository adminRepository) {
        this.repository = repository;
        this.adminRepository = adminRepository;
    }

    @Override
    public User register(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repository.save(user);
    }
    
    @Override
    public String login(String email, String password) {

        // If email exists in Admin table
        if (adminRepository.existsByEmail(email)) {
            return "This email belongs to Admin";
        }

        Optional<User> optionalUser = repository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User dbUser = optionalUser.get();

        if (!dbUser.getPassword().equals(password)) {
            return "Invalid password";
        }

        return "User login successful";
    }
}
