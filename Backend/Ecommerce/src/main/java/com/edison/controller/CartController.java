package com.edison.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.Cart;
import com.edison.model.Order;
import com.edison.service.CartService;

@RestController
@CrossOrigin("http://localhost:3001")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/cart/{productId}")
	public Cart addItemToCart(@PathVariable("productId") Long productId) {
		return cartService.addItemToCart(productId);
	}

//	@GetMapping("/cart")
//	public Cart getCartItems() {
//		return cartService.getCartItems();
//	}
	@GetMapping("/cart/all")
	public List<Cart> getallCartItems() {
		return cartService.getallCartItems();
	}

	@PostMapping("/cart/checkout/{cartId}")
	public Order proceedToCheckout(@PathVariable("cartId") Long cartId) {
		return cartService.proceedToCheckout(cartId);
	}
	
	@DeleteMapping("cart/{cartId}")
	public  void deleteCartById(@PathVariable("cartId") Long cartId) {
		cartService.deleteCart(cartId);
	}
}
