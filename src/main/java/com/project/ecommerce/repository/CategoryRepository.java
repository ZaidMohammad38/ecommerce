package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Long>
{
    Optional<Category> findByName(String name);

    void deleteById(Long id);

    
    Optional<Category> findById(Long id);
}
