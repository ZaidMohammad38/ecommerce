package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Category;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategory(Long id);
}
