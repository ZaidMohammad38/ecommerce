package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Product;
import com.project.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getProductById(id);
    }
    
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
    					  @RequestBody Product product)
    {
    	return service.updateProduct(id, product);
    }
    
    @GetMapping("/search/{name}")
    public List<Product> search(@PathVariable String name)
    {
    	return service.searchByName(name);		
    }
    
    @GetMapping("/category/{categoryId}")
    public List<Product> byCategory(@PathVariable Long categoryId)
    {
    	return service.getByCategory(categoryId);
    }
    

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return "Product deleted successfully";
    }
}