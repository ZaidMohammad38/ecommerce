package com.project.ecommerce.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Admin;
import com.project.ecommerce.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController 
{
	
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/register") //localhost:8080/api/admin/register
    public Admin register(@RequestBody Admin admin) {
        return service.register(admin);
    }
    

    @PostMapping("/login") //localhost:8080/api/admin/login
    public String login(@RequestBody Map<String, String> data) {
        return service.login(
                data.get("email"),
                data.get("password")
        );
    }
}
