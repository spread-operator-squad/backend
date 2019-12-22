package com.enigma.controller;

import com.enigma.entities.Review;
import com.enigma.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public Review saveReview(@RequestBody Review review){
        return this.reviewService.saveReview(review);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id){
        return this.reviewService.findReviewById(id);
    }

    @GetMapping
    public List<Review> getAllReview(){
        return this.reviewService.findAllReview();
    }

    @PutMapping
    public Review updateReview(@RequestBody Review review){
        return this.reviewService.updateReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id){
        this.reviewService.deleteReview(id);
    }
}
