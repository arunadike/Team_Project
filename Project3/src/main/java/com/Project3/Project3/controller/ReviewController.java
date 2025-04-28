package com.Project3.Project3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Project3.Project3.model.Review;
import com.Project3.Project3.service.ReviewService;
import com.Project3.Project3.model.Booking;
import com.Project3.Project3.repository.BookingRepository;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/reviewPost")
    @Transactional
    public ResponseEntity<String> reviewPost(@RequestBody @Valid Review review) {
        try {
            Booking booking = review.getBooking();
            if (booking != null && booking.getBookingId() != 0) {
                Booking existingBooking = bookingRepository.findById(booking.getBookingId()).orElse(null);
                if (existingBooking != null) {
                    review.setBooking(existingBooking);
                }
            }
            reviewService.saveData(review);
            return new ResponseEntity<>("Review saved successfully", HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            //  Handle the optimistic locking failure specifically.
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save review: Booking data was modified concurrently. Please try again.", HttpStatus.CONFLICT); // Return 409 Conflict
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save review: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviewGet")
    public List<Review> reviewGet() {
        return reviewService.returnData();
    }
}
