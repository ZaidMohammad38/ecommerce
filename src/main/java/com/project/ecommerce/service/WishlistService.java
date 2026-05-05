package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Wishlist;

public interface WishlistService {

    Wishlist add(Wishlist wishlist);

    List<Wishlist> getUser(Long userId);

    void delete(Long id);
}
