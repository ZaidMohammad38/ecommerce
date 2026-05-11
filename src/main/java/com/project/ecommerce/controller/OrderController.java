package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Order;
import com.project.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController 
{
    private final OrderService service;

    public OrderController(OrderService service) 
    {
        this.service = service;
    }

    @PostMapping
    public Order place(@RequestBody Order order) {
        return service.placeOrder(order);
    }

    @GetMapping
    public List<Order> all() {
        return service.getAllOrders();
    }

    @GetMapping("/user/{userId}")//localhost/api/orders/user/1
    public List<Order> byUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @PutMapping("/{id}")//localhost/api/orders/1?
    public Order update(@PathVariable Long id,@RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
