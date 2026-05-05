package com.project.ecommerce.service;

import com.project.ecommerce.entity.Admin;

public interface AdminService {

    Admin register(Admin admin);

    String login(String email, String password);
}
