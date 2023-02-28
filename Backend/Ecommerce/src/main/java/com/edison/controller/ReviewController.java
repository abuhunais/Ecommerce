package com.edison.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.Review;
import com.edison.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("http://localhost:3001")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{productId}")
    public Review addProductReview(@PathVariable Long productId, @RequestBody Review review) {
        return reviewService.addProductReview(productId, review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateProductCommentAndRating(@PathVariable Long reviewId, 
                                                                @RequestBody Review reviewDto) {
        Review updatedReview = reviewService.updateProductCommentAndRating(reviewId, 
                                                                            reviewDto.getComment(), 
                                                                            reviewDto.getRating());
        return ResponseEntity.ok(updatedReview);
    }


    @GetMapping("/{productId}")
    public List<Review> getProductReviews(@PathVariable Long productId) {
        return reviewService.getProductReviews(productId);
    }
}

