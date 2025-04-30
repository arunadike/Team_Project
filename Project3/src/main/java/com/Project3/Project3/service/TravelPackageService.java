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

	// Use the combined search and filter method from the repository
	public List<TravelPackage> searchAndFilterPackages(String title, Integer minDuration, Integer maxDuration, String service) {
		return travelPackageRepository.searchAndFilter(title, minDuration, maxDuration, service);
	}

	public List<TravelPackage> getAllPackages() {
		return travelPackageRepository.findAll();
	}

	public Optional<TravelPackage> getPackageById(int packageId) {
		return travelPackageRepository.findById(packageId);
	}

	public void createPackage(TravelPackage travelPackage) {
		travelPackageRepository.save(travelPackage);
	}

	public void updatePackage(TravelPackage travelPackage) {
		travelPackageRepository.save(travelPackage);
	}

	public void deletePackage(int packageId) {
		travelPackageRepository.deleteById(packageId);
	}
}
