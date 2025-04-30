package com.Project3.Project3.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Project3.Project3.model.Booking;
import com.Project3.Project3.model.Review;
import com.Project3.Project3.repository.BookingRepository;
import com.Project3.Project3.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository; // Inject BookingRepository

    public void saveData(Review review) {
        // Assuming the bookingId is passed within the review object
        if (review.getBooking() != null && review.getBooking().getBookingId() != 0) {
            Booking existingBooking = bookingRepository.findById(review.getBooking().getBookingId()).orElse(null);
            if (existingBooking != null) {
                review.setBooking(existingBooking);
                review.setReviewDate(new Date()); // Set the review date on the backend
                review.setReviewComment(review.getReviewComment());
                reviewRepository.save(review);
                System.out.println("Review Saved");
            } else {
                // Handle the case where the booking ID is invalid
                throw new IllegalArgumentException("Invalid booking ID: " + review.getBooking().getBookingId());
            }
        } else {
            // Handle the case where booking information is missing
            throw new IllegalArgumentException("Booking information is required for the review.");
        }
    }

    public List<Review> returnData() {
        List<Review> reviews = (List<Review>) reviewRepository.findAll();
        System.out.println(reviews);
        return reviews;
    }

    // Corrected method name to match ReviewRepository
    public List<Review> getReviewsByPackageId(int packageId) {
        return reviewRepository.findByPackage1_PackageId(packageId);
    }
}