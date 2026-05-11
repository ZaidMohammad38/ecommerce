package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Order;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(Long userId);

    Order updateStatus(Long id, String status);
}
