package com.enigma.controller;

import com.enigma.entities.Item;
import com.enigma.entities.Review;
import com.enigma.services.ItemService;
import com.enigma.services.ReviewService;
import com.enigma.services.impl.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveReview(@RequestBody Review review){
        CustomResponse response = this.reviewService.saveReview(review);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getReviewById(@PathVariable Integer id){
        CustomResponse response = this.reviewService.findReviewById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllReview(){
        CustomResponse response = this.reviewService.findAllReview();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateReview(@RequestBody Review review){
        CustomResponse response = this.reviewService.updateReview(review);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteReview(@PathVariable Integer id){
        CustomResponse response = this.reviewService.deleteReview(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
