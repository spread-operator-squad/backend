package com.enigma.services;

import com.enigma.entities.Review;

public interface ReviewService {
    CustomResponse saveReview(Review review);
    CustomResponse findAllReview();
    CustomResponse deleteReview(Integer id);
    CustomResponse findReviewById(Integer id);
    CustomResponse updateReview(Review review);
}
