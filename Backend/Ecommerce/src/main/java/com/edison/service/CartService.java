package com.edison.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edison.model.Cart;
import com.edison.model.CartItem;
import com.edison.model.Order;
import com.edison.model.OrderItem;
import com.edison.model.Product;
import com.edison.repo.CartRepository;
import com.edison.repo.ProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderService orderService;

	public Cart addItemToCart(Long productId) {
		Product product = productRepository.findById(productId).get();
		Cart cart = cartRepository.findById(1L).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setCartItems(new ArrayList<>());
			return newCart;
		});
	
		CartItem cartItem = new CartItem();
		
		cartItem.setProduct(product);
		
		cartItem.setQuantity(1);
		cart.getCartItems().add(cartItem);
		cart.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		cartRepository.save(cart);
		return cart;
	}

	public Cart getCartItems() {
		return cartRepository.findById(1L).orElse(new Cart());
	}
	public List<Cart> getallCartItems() {
		return cartRepository.findAll();
	}

	public Order proceedToCheckout(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElse(null);
		if (cart == null || cart.getCartItems() == null) {
			return null;
		}
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem item : cart.getCartItems()) {
			orderItems.add(new OrderItem(item.getProduct(), item.getQuantity()));
		}

		Order order = orderService.createOrder1(orderItems);
		cartRepository.deleteById(cartId);

		return order;
	}
	
	public void deleteCart(long cartId) {
		 cartRepository.deleteById(cartId);
	}


	

}
