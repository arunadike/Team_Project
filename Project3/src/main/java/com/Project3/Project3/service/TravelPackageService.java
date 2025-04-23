package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

	@Autowired
	private TravelPackageRepository travelPackageRepository;

	public void saveData(TravelPackage travelPackage) {
		travelPackageRepository.save(travelPackage);
	}

	public List<TravelPackage> returnData() {
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}

	public void createPackage(TravelPackage travelPackage) {
		travelPackageRepository.save(travelPackage);
	}

	public List<TravelPackage> packageDisplay() {
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}

	// New method to get a single package by ID
	public Optional<TravelPackage> getPackageById(int packageId) {
		return travelPackageRepository.findById(packageId);
	}
}
