package com.Project3.Project3.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Project3.Project3.model.Booking;
import com.Project3.Project3.service.BookingService;
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.http.HttpStatus; // Import HttpStatus
import java.util.Date;

@RestController
@CrossOrigin //  Configure CORS at the controller level.  This is important.
public class BookingController {

	@Autowired
	private BookingService bookingService;
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class); // Corrected logger name


	@GetMapping("/bookingGet")
    public ResponseEntity<List<Booking>> getBooking() {
        logger.info("Received request to get all bookings");
        try {
            List<Booking> bookings = bookingService.returnData();
            logger.info("Successfully retrieved {} bookings.", bookings.size());
            return new ResponseEntity<>(bookings, HttpStatus.OK); // Use ResponseEntity
        } catch (Exception e) {
            logger.error("An error occurred while retrieving bookings: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<String> post(@RequestBody Booking booking) {
        logger.info("Received request to create a booking: {}", booking);
        try {
            bookingService.saveData(booking);
            logger.info("Booking saved successfully");
            return new ResponseEntity<>("Booking saved successfully", HttpStatus.CREATED); // Use ResponseEntity
        } catch (Exception e) {
            logger.error("Failed to save booking: {}", e.getMessage(), e);
            return new ResponseEntity<>("Failed to save booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }
}
