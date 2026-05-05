package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.project.ecommerce.entity.OrderEntity;
import com.project.ecommerce.service.OrderService;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderEntity place(
            @RequestBody OrderEntity order) {
        return service.placeOrder(order);
    }

    @GetMapping
    public List<OrderEntity> all() {
        return service.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderEntity> byUser(
            @PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @PutMapping("/{id}")
    public OrderEntity update(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
