package com.enigma.services.impl;

import com.enigma.constans.ResponseReviewConstants;
import com.enigma.entities.Review;
import com.enigma.entities.Store;
import com.enigma.entities.User;
import com.enigma.repositories.ReviewRepository;
import com.enigma.services.ReviewService;
import com.enigma.services.StoreService;
import com.enigma.services.UserService;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.impl.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public CustomResponse saveReview(Review review) {
        if (!(review.getUserId().isEmpty())) review.setUsers(reviewHasUser((User) this.userService.findUserById(review.getUserId()).getData()));
        if (review.getStoreId() != null) review.setStores(reviewHasStore((Store) this.storeService.findStoreById(review.getStoreId()).getData()));
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseReviewConstants.SUCCESS_SAVE_REVIEW), this.reviewRepository.save(review));
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
    public CustomResponse findAllReview() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseReviewConstants.SUCCESS_GET_REVIEWS), this.reviewRepository.findAll());
    }

    @Override
    public CustomResponse deleteReview(Integer id) {
        this.reviewRepository.delete((Review) this.findReviewById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseReviewConstants.SUCCESS_DELETE_REVIEW));
    }

    @Override
    public CustomResponse findReviewById(Integer id) {
        if (!(this.reviewRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Review is not found!"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseReviewConstants.SUCCESS_GET_REVIEW), this.reviewRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateReview(Review review) {
        if (this.findReviewById(review.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findReviewById(review.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseReviewConstants.SUCCESS_UPDATE_REVIEW), this.saveReview(review).getData());
    }
}
