package com.edison.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edison.model.Product;
import com.edison.repo.ProductRepository;
import com.edison.repo.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	
    //private final ReviewRepository  reviewRepository;
	private final ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}


	public List<Product> getProductsByCategoryAndSubcategory(String category, String subcategory) {
		return productRepository.findByCategoryAndSubcategory(category, subcategory);
	}

	public List<Product> searchProducts(String keyword) {
		return productRepository.findByNameContainingIgnoreCase(keyword);
	}
	
	public List<Product> searchProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow();
	}

	public Product updateProduct(Long productId, Product product) {
		Product existingProduct = getProductById(productId);
		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setSubcategory(product.getSubcategory());
		existingProduct.setStock(product.getStock());
		return productRepository.save(existingProduct);
	}

	public Product updateProductStock(Long productId, int stock) {
		Product existingProduct = getProductById(productId);
		existingProduct.setStock(stock);
		return productRepository.save(existingProduct);
	}
	
	public void deleteProduct (Long productId) {
		productRepository.deleteById(productId);
	}

}