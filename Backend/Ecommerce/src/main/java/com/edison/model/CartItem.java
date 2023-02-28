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
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private double subtotal;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Cart cart;

	public CartItem( Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.subtotal = product.getPrice() * quantity;
		}

}
