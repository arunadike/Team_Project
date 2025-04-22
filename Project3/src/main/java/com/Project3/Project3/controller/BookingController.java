package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Booking;
import com.Project3.Project3.service.BookingService;

@RestController
public class BookingController {
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/bookingGet")
	public List<Booking> get()
	{
		return  bookingService.returnData();
	}
	
	@PostMapping("/booking")
	public void post(@RequestBody Booking booking)
	{
		bookingService.saveData(booking);
	}

}
