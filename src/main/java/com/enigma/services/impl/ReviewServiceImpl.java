package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageService;
import com.enigma.entities.Review;
import com.enigma.entities.Store;
import com.enigma.entities.User;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.ReviewRepository;
import com.enigma.services.ReviewService;
import com.enigma.services.StoreService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    StoreService storeService;

    @Override
    public List<Review> findAllReview() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review saveReview(Review review) {
        if (!(review.getUserId().isEmpty())) review.setUsers(reviewHasUser(this.userService.findUserById(review.getUserId())));
        if (review.getStoreId() != null) review.setStores(reviewHasStore(this.storeService.findStoreById(review.getStoreId())));
        return this.reviewRepository.save(review);
    }

    private Set<Store> reviewHasStore(Store store) {
        Set<Store> stores = new HashSet<>();
        stores.add(store);
        return  stores;
    }

    private Set<User> reviewHasUser(User user) {
        Set<User> users = new HashSet<>();
        users.add(user);
        return users;
    }

    @Override
    public Review findReviewById(Integer id) {
        if (!(this.reviewRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageService.FAILED_GET_SERVICE);
        return this.reviewRepository.findById(id).get();
    }

    @Override
    public Review updateReview(Review review) {
        this.findReviewById(review.getId());
        return this.saveReview(review);
    }

    @Override
    public void deleteReview(Integer id) {
        this.reviewRepository.delete(this.findReviewById(id));
    }
}
