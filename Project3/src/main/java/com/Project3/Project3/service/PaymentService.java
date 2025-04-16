package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Payment;
import com.Project3.Project3.model.Payment1;
import com.Project3.Project3.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;

	public void saveData(Payment1 payment) {
		paymentRepository.save(payment);
		
	}

	public List<Payment1> returnData() {
		// TODO Auto-generated method stub
		return (List<Payment1>) paymentRepository.findAll();
	}
	
	
	
	

}
