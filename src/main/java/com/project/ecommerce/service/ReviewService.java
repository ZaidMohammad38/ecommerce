package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Review;

public interface ReviewService {

    Review add(Review review);

    List<Review> getByProduct(Long productId);

    void delete(Long id);

    Double average(Long productId);
}
