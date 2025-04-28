package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Insurance;
import com.Project3.Project3.repository.InsuranceRepository;

@Service
public class InsuranceService {

	@Autowired
	private InsuranceRepository insuranceRepository;

	public void saveData(Insurance insurance) {
		insuranceRepository.save(insurance);

	}

	public List<Insurance> returnData() {
		// TODO Auto-generated method stub
		return (List<Insurance>) insuranceRepository.findAll();
	}

}
