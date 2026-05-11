package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Review;
import com.project.ecommerce.service.ReviewService;

@RestController
@RequestMapping("/api/reviews") //localhost/api/reviews
public class ReviewController 
{
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review add(@RequestBody Review review) {
        return service.add(review);
    }

    @GetMapping("/{productId}")//localhost/api/reviews/1
    public List<Review> get(@PathVariable Long productId) {
        return service.getByProduct(productId);
    }

    @DeleteMapping("/{id}")//localhost/api/reviews/1
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Review deleted successfully";
    }

    @GetMapping("/average/{productId}")//localhost/api/reviews/average/1
    public Double average(@PathVariable Long productId) {
        return service.average(productId);
    }
}
