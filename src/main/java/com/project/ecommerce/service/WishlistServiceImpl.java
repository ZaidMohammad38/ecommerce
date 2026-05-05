package com.project.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ecommerce.entity.Wishlist;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.repository.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistServiceImpl(WishlistRepository repository, UserRepository userRepository,
			ProductRepository productRepository) {
		super();
		this.repository = repository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
    public Wishlist add(Wishlist wishlist) {

        Long userId = wishlist.getUser().getId();
        Long productId = wishlist.getProduct().getId();

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }

        return repository.save(wishlist);
    }

    public List<Wishlist> getUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
