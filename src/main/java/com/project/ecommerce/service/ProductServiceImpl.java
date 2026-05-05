package com.project.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entity.Product;
import com.project.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, Product product)
    {
    	Product dbProduct = repository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Product not found"));
    	
    	dbProduct.setName(product.getName());
    	dbProduct.setDescription(product.getDescription());
    	dbProduct.setPrice(product.getPrice());
    	dbProduct.setStock(product.getStock());
    	dbProduct.setImageUrl(product.getImageUrl());
    	dbProduct.setCategory(product.getCategory());
    	
    	return repository.save(dbProduct);
    }
    

	@Override
	public List<Product> searchByName(String name) 
	{
		return repository.findByNameContainingIgnoreCase(name);
	}


	@Override
	public List<Product> getByCategory(Long categoryId)
	{
		return repository.findByCategoryId(categoryId);
	}
	
	
	public void deleteProduct(Long id) 
	{
		repository.deleteById(id);
	}
}
