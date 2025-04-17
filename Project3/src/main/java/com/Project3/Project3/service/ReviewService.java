package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Review;
import com.Project3.Project3.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public void saveData(Review review) {
        reviewRepository.save(review);
        System.out.println("Review Saved");
    }

    public List<Review> returnData() {
        return (List<Review>) reviewRepository.findAll();
    }
}