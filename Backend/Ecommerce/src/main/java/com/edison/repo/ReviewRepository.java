package com.edison.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edison.model.Product;
import com.edison.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findByProduct(Product product);


}
