package com.edison.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.Product;
import com.edison.service.ProductService;

@RestController
@CrossOrigin("http://localhost:3001")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@GetMapping("/products/{category}/{subcategory}")
	public List<Product> getProductsByCategoryAndSubcategory(@PathVariable("category") String category,
			@PathVariable("subcategory") String subcategory) {
		return productService.getProductsByCategoryAndSubcategory(category, subcategory);
	}

	@GetMapping("/products/search")
	public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
		return productService.searchProducts(keyword);
	}

	@GetMapping("/products/all")
	public List<Product> searchProducts() {
		return productService.searchProducts();

	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}

	@PutMapping("/products/{productId}")
	public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}

	@PutMapping("/products/{productId}/stock")
	public Product updateProductStock(@PathVariable("productId") Long productId, @RequestParam("stock") int stock) {
		return productService.updateProductStock(productId, stock);
	}
	
	@DeleteMapping("products/{productId}")
	public void deleteProductById(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
	}

}
