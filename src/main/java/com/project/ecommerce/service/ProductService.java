package com.project.ecommerce.service;

import java.util.List;

import com.project.ecommerce.entity.Product;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);
    
    Product updateProduct(Long id, Product product);
    
    List<Product> searchByName(String name);
    
    List<Product> getByCategory(Long categoryId);

    void deleteProduct(Long id);
}