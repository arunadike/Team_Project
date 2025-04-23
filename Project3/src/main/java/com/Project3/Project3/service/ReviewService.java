package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Review;
import com.Project3.Project3.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void saveData(Review review) {
        reviewRepository.save(review);
        System.out.println("Review Saved");
    }

    public List<Review> returnData() {
        System.out.println(reviewRepository.findAll());
        return (List<Review>) reviewRepository.findAll();
    }

    //  Corrected method name to match ReviewRepository
    public List<Review> getReviewsByPackageId(int packageId) {
        return reviewRepository.findByPackage1_PackageId(packageId);
    }
}