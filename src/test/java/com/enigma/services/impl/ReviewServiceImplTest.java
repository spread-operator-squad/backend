package com.enigma.services.impl;

import com.enigma.entities.Review;
import com.enigma.entities.Store;
import com.enigma.entities.User;
import com.enigma.repositories.ReviewRepository;
import com.enigma.services.ReviewService;
import com.enigma.services.StoreService;
import com.enigma.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReviewServiceImplTest {

    @MockBean
    ReviewRepository reviewRepository;

    @MockBean
    UserService userService;

    @MockBean
    StoreService storeService;

    @SpyBean
    ReviewService reviewService;

    @Test
    void findAllReview_should_call_reviewRepository_findAll_once() {
        reviewService.findAllReview();
        Mockito.verify(reviewRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveReview_should_call_reviewRepository_save_once() {
        Review sample = new Review();
        sample.setMessage("Lorem ipsum");
        sample.setStoreId(1);
        sample.setUserId("id");
        Mockito.when(userService.findUserById("id")).thenReturn(new User());
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        reviewService.saveReview(sample);
        Mockito.verify(reviewRepository, Mockito.times(1)).save(sample);
    }

    @Test
    void findReviewById_should_call_reviewRepository_findById_twice() {
        Review sample = new Review();
        sample.setMessage("Lorem ipsum");
        sample.setStoreId(1);
        sample.setUserId("id");
        Mockito.when(userService.findUserById("id")).thenReturn(new User());
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        Mockito.when(reviewRepository.findById(1)).thenReturn(Optional.of(sample));
        reviewService.findReviewById(1);
        Mockito.verify(reviewRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateReview_should_call_reviewService_findReviewById_once() {
        Review sample = new Review();
        sample.setId(1);
        sample.setMessage("Lorem ipsum");
        sample.setStoreId(1);
        sample.setUserId("id");
        Mockito.when(userService.findUserById("id")).thenReturn(new User());
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        Mockito.when(reviewRepository.findById(1)).thenReturn(Optional.of(sample));
        reviewService.updateReview(sample);
        Mockito.verify(reviewService, Mockito.times(1)).findReviewById(1);
    }

    @Test
    void updateReview_should_call_reviewService_saveReview_once() {
        Review sample = new Review();
        sample.setId(1);
        sample.setMessage("Lorem ipsum");
        sample.setStoreId(1);
        sample.setUserId("id");
        Mockito.when(userService.findUserById("id")).thenReturn(new User());
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        Mockito.when(reviewRepository.findById(1)).thenReturn(Optional.of(sample));
        reviewService.updateReview(sample);
        Mockito.verify(reviewService, Mockito.times(1)).updateReview(sample);
    }

    @Test
    void deleteReview_should_call_reviewRepository_delete_once() {
        Review sample = new Review();
        sample.setId(1);
        sample.setMessage("Lorem ipsum");
        sample.setStoreId(1);
        sample.setUserId("id");
        Mockito.when(reviewRepository.findById(1)).thenReturn(Optional.of(sample));
        reviewService.deleteReview(1);
        Mockito.verify(reviewRepository, Mockito.times(1)).delete(sample);
    }
}
