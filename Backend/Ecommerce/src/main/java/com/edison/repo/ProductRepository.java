package com.edison.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edison.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryAndSubcategory(String category, String subcategory);
	List<Product> findByNameContainingIgnoreCase(String keyword);
}
