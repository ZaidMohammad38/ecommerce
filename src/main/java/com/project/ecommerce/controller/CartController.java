package com.project.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.entity.Cart;
import com.project.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public Cart add(@RequestBody Cart cart) {
        return service.addToCart(cart);
    }

    @GetMapping("/{userId}") //localhost/api/cart/1
    public List<Cart> getCart(@PathVariable Long userId) {
        return service.getUserCart(userId);
    }

    @DeleteMapping("/item/{cartId}")
    public String remove(@PathVariable Long cartId) {
        service.removeItem(cartId);
        return "Item removed from cart";
    }

    @DeleteMapping("/clear/{userId}")
    public String clear(@PathVariable Long userId) {
        service.clearCart(userId);
        return "Cart cleared";
    }
}
