package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.OrderEntity;


public interface OrderService {

    OrderEntity placeOrder(OrderEntity order);

    List<OrderEntity> getAllOrders();

    List<OrderEntity> getOrdersByUser(Long userId);

    OrderEntity updateStatus(Long id, String status);
}
