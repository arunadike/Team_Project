package com.Project3.Project3.controller;

import java.util.List;
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

	@GetMapping("/bookingGet")
	public ResponseEntity<List<Booking>> get() {
		try {
			List<Booking> bookings = bookingService.returnData();
			return new ResponseEntity<>(bookings, HttpStatus.OK); // Use ResponseEntity
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
		}
	}

	@PostMapping("/booking")
	public ResponseEntity<String> post(@RequestBody Booking booking) {
		try {
			System.out.println("Received Booking object: " + booking);
			bookingService.saveData(booking);
			return new ResponseEntity<>("Booking saved successfully", HttpStatus.CREATED); // Use ResponseEntity
		} catch (Exception e) {
			e.printStackTrace(); // Log the error
			return new ResponseEntity<>("Failed to save booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
		}
	}
}
