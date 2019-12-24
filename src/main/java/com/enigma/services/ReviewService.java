package com.enigma.services;

import com.enigma.entities.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReview();
    Review saveReview(Review review);
    Review findReviewById(Integer id);
    Review updateReview(Review review);
    void deleteReview(Integer id);
}
