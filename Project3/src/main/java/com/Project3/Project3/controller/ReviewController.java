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
import com.Project3.Project3.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/reviewPost")
    @Transactional
    public ResponseEntity<String> reviewPost(@RequestBody @Valid Review review) {
        try {
            // 1.  Null Check for Booking
            if (review.getBooking() == null) {
                return new ResponseEntity<>("Failed to save review: Booking is required.", HttpStatus.BAD_REQUEST);
            }

            // 2.  Check for existing review using the repository
            if (reviewRepository.findByBooking(review.getBooking()) != null) {
                return new ResponseEntity<>("Failed to save review: A review for this booking already exists.", HttpStatus.BAD_REQUEST);
            }

            // 3.  Get Booking from DB
            Booking booking = bookingRepository.findById(review.getBooking().getBookingId()).orElse(null);
            if(booking == null){
                return new ResponseEntity<>("Failed to save review: Booking does not exist.", HttpStatus.BAD_REQUEST);
            }
            review.setBooking(booking);

            // 4. Save the review through the service (which should just call the repository)
            reviewService.saveData(review);
            return new ResponseEntity<>("Review saved successfully", HttpStatus.OK);

        } catch (ObjectOptimisticLockingFailureException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save review: Booking data was modified concurrently. Please try again.", HttpStatus.CONFLICT);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save review: A review for this booking already exists.", HttpStatus.BAD_REQUEST);
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
