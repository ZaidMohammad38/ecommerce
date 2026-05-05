package com.project.ecommerce.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entity.OrderEntity;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.ProductRepository;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderEntity placeOrder(OrderEntity order) {

        Product product = productRepository
                .findById(order.getProduct().getId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        order.setTotalPrice(
                product.getPrice()
                        * order.getQuantity()
        );

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderEntity updateStatus(
            Long id,
            String status) {

        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}