package com.edison.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.Order;
import com.edison.model.OrderItem;
import com.edison.service.OrderService;

@RestController
@CrossOrigin("http://localhost:3001")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public List<Order> getOrderHistory() {
		return orderService.getOrderHistory();
	}

	@GetMapping("/orders/{orderId}")
	public Order getOrderStatus(@PathVariable("orderId") Long orderId) {
		return orderService.getOrderStatus(orderId);
	}

	@PostMapping("/order/{customerId}/{productId}")
	public Order createOrder(@PathVariable("productId") Long productId, @PathVariable("customerId") Long customerId) {
	return orderService.createOrder(productId, customerId);
	}
}
