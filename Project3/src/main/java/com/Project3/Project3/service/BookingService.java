package com.Project3.Project3.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Booking;
import com.Project3.Project3.repository.BookingRepository;

@Service
public class BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	BookingRepository bookingRepository;

	public List<Booking> returnData() {
		try {
			return (List<Booking>) bookingRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching bookings: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void saveData(Booking booking) {
		try {
			bookingRepository.save(booking);
			logger.info("Booking saved successfully");
		} catch (Exception e) {
			logger.error("Error saving booking: {}", e.getMessage(), e);
			throw e;
		}
	}
}
