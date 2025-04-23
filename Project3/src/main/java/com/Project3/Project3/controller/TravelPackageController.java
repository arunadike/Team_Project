package com.Project3.Project3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.model.Review; // Import the Review model
import com.Project3.Project3.service.TravelPackageService;
import com.Project3.Project3.service.ReviewService; // Import ReviewService

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TravelPackageController {

	@Autowired
	private TravelPackageService travelPackageService;

	@Autowired
	private ReviewService reviewService; // Add ReviewService

	@PostMapping("/travelPost")
	public void travelPost(@RequestBody TravelPackage travelPackage) {
		travelPackageService.saveData(travelPackage);
	}

	@GetMapping("/travelGet")
	public List<TravelPackage> travelGet() {
		return travelPackageService.returnData();
	}

	@PostMapping("/packageCreation")
	public void packageCreation(@Valid @RequestBody TravelPackage travelPackage) {
		System.out.println("working");
		travelPackageService.createPackage(travelPackage);
	}

	@GetMapping("/packageDisplay")
	public List<TravelPackage> packageDisplay() {
		return travelPackageService.packageDisplay();
	}

	// New endpoint to get a single package by ID
	@GetMapping("/package/{packageId}")
	public ResponseEntity<TravelPackage> getPackageById(@PathVariable int packageId) {
		Optional<TravelPackage> packageOptional = travelPackageService.getPackageById(packageId); // Corrected method call
		if (packageOptional.isPresent()) {
			return ResponseEntity.ok(packageOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// New endpoint to get reviews for a package
	@GetMapping("/reviews/{packageId}")
	public ResponseEntity<List<Review>> getReviewsByPackageId(@PathVariable int packageId) {
		System.out.println("Fetching reviews for package ID: " + packageId); // Added logging
		List<Review> reviews = reviewService.getReviewsByPackageId(packageId);
		System.out.println("Reviews found: " + reviews.size()); // Added logging
		return ResponseEntity.ok(reviews);
	}
}

