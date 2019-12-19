package com.enigma.services;

import com.enigma.entities.Review;
import com.enigma.services.impl.CustomResponse;

public interface ReviewService {
    CustomResponse saveReview(Review review);
    CustomResponse findAllReview();
    CustomResponse deleteReview(Integer id);
    CustomResponse findReviewById(Integer id);
    CustomResponse updateReview(Review review);
}
