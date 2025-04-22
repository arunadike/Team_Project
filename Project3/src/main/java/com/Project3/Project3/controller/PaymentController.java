package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Payment1;
import com.Project3.Project3.service.PaymentService;

import jakarta.validation.Valid;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/paymentPost")
	public void paymentPost(@RequestBody @Valid Payment1 payment)
	{
		paymentService.saveData(payment);
		
	}
	
	@GetMapping("/paymentGet")
	public List<Payment1> paymentGet()
	{
		return paymentService.returnData();
	}

}
