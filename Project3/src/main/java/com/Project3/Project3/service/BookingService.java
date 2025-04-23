package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Booking;
import com.Project3.Project3.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	public List<Booking> returnData() {
		// TODO Auto-generated method stub
		return (List<Booking>) bookingRepository.findAll();
	}

	public void saveData(Booking booking) {
		// TODO Auto-generated method stub
		bookingRepository.save(booking);
		System.out.println("Done");
	}


}