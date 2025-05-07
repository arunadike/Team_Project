package com.Project3.Project3.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Booking;
import com.Project3.Project3.model.Review;
import com.Project3.Project3.repository.BookingRepository;
import com.Project3.Project3.repository.ReviewRepository; // Import ReviewRepository
import com.Project3.Project3.service.ReviewService;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ReviewRepository reviewRepository; // Add ReviewRepository
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ReviewController.class); // Corrected logger
																									// name

	@PostMapping("/reviewPost")
	@Transactional // Use default isolation level (READ COMMITTED is usually fine)
	public ResponseEntity<String> reviewPost(@RequestBody @Valid Review review) {
		logger.info("Received request to save a review: {}", review);
		try {
			Booking booking = review.getBooking();
			if (booking != null && booking.getBookingId() != 0) {
				logger.debug("Fetching booking with ID: {}", booking.getBookingId());
				Booking existingBooking = bookingRepository.findById(booking.getBookingId()).orElse(null);
				if (existingBooking != null) {
					logger.debug("Found booking: {}", existingBooking);
					review.setBooking(existingBooking);
				} else {
					logger.warn("Booking with id {} not found", booking.getBookingId());
					return new ResponseEntity<>("Failed to save review: Booking does not exist.",
							HttpStatus.BAD_REQUEST);
				}
			}

			// *** IMPORTANT: Check for existing review *before* saving ***
			if (reviewRepository.findByBooking(review.getBooking()) != null) {
				logger.warn("A review for this booking already exists.");
				return new ResponseEntity<>("Failed to save review: A review for this booking already exists.",
						HttpStatus.BAD_REQUEST);
			}

			reviewService.saveData(review); // This should only contain reviewRepository.save(review);
			logger.info("Review saved successfully");
			return new ResponseEntity<>("Review saved successfully", HttpStatus.OK);
		} catch (ObjectOptimisticLockingFailureException e) {
			logger.error("Failed to save review due to concurrent modification: {}", e.getMessage(), e);
			return new ResponseEntity<>(
					"Failed to save review: Booking data was modified concurrently. Please try again.",
					HttpStatus.CONFLICT);
		} catch (DataIntegrityViolationException e) {
			logger.error("Failed to save review due to data integrity violation: {}", e.getMessage(), e);
			return new ResponseEntity<>("Failed to save review: A review for this booking already exists.",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Failed to save review: {}", e.getMessage(), e);
			return new ResponseEntity<>("Failed to save review: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reviewGet")
	public ResponseEntity<List<Review>> reviewGet() {
		logger.info("Received request to get all reviews");
		try {
			List<Review> reviews = reviewService.returnData();
			logger.info("Successfully retrieved {} reviews.", reviews.size());
			return ResponseEntity.ok(reviews);
		} catch (Exception e) {
			logger.error("An error occurred while retrieving all reviews: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
