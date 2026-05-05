package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Cart;

public interface CartService {

    Cart addToCart(Cart cart);

    List<Cart> getUserCart(Long userId);

    void removeItem(Long cartId);

    void clearCart(Long userId);
}