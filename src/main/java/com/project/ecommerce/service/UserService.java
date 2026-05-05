package com.project.ecommerce.service;

import com.project.ecommerce.entity.User;

public interface UserService {

    User register(User user);

    String login(String email, String password);
}
