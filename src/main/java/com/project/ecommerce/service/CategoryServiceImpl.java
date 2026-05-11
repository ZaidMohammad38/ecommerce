package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Category;
import com.project.ecommerce.repository.CategoryRespository;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRespository repository;

    public CategoryServiceImpl(CategoryRespository repository) {
        this.repository = repository;
    }

    @Override
    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    
    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}