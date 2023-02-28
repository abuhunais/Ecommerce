package com.edison.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private int quantity;
	private double price;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Order order;
	
	
	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.productName = product.getName();
		this.quantity = quantity;
		this.price = product.getPrice();
	}

}
