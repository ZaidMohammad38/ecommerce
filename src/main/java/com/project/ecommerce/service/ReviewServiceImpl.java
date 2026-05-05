package com.project.ecommerce.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entity.Review;
import com.project.ecommerce.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review add(Review review) 
    {
        return repository.save(review);
    }

    @Override
    public List<Review> getByProduct(Long productId) 
    {
        return repository.findByProductId(productId);
    }

    @Override
    public void delete(Long id) 
    {
        repository.deleteById(id);
    }

    @Override
    public Double average(Long productId) 
    {
        return repository.getAverageRating(productId);
    }
}
