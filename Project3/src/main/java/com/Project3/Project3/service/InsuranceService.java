package com.Project3.Project3.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Insurance;
import com.Project3.Project3.repository.InsuranceRepository;

@Service
public class InsuranceService {

	private static final Logger logger = LoggerFactory.getLogger(InsuranceService.class);

	@Autowired
	private InsuranceRepository insuranceRepository;

	public void saveData(Insurance insurance) {
		try {
			insuranceRepository.save(insurance);
			logger.info("Insurance saved successfully");
		} catch (Exception e) {
			logger.error("Error saving insurance: {}", e.getMessage(), e);
			throw e;
		}
	}

	public List<Insurance> returnData() {
		try {
			return (List<Insurance>) insuranceRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching insurances: {}", e.getMessage(), e);
			throw e;
		}
	}

}
