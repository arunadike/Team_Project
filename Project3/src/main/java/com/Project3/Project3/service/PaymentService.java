package com.Project3.Project3.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Payment;
import com.Project3.Project3.repository.PaymentRepository;

@Service
public class PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	PaymentRepository paymentRepository;

	public void saveData(Payment payment) {
		try {
			paymentRepository.save(payment);
			logger.info("Payment saved successfully");
		} catch (Exception e) {
			logger.error("Error saving payment: {}", e.getMessage(), e);
			throw e;
		}
	}

	public List<Payment> returnData() {
		try {
			return (List<Payment>) paymentRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching payments: {}", e.getMessage(), e);
			throw e;
		}
	}

}
