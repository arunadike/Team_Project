package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Insurance;
import com.Project3.Project3.service.InsuranceService;

import jakarta.validation.Valid;

@RestController
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;

	@GetMapping("/insuranceGet")
	public List<Insurance> insuranceGet() {
		return insuranceService.returnData();
	}

	@PostMapping("/insurancePost")
	public void insurancePost(@RequestBody @Valid Insurance insurance) {
		insuranceService.saveData(insurance);
	}
}