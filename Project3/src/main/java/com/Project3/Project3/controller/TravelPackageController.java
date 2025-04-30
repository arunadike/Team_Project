package com.Project3.Project3.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.model.Review;
import com.Project3.Project3.service.TravelPackageService;
import com.Project3.Project3.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TravelPackageController {

	@Autowired
	private TravelPackageService travelPackageService;
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/travelPost")
	public void travelPost(@RequestBody TravelPackage travelPackage) {
		travelPackageService.createPackage(travelPackage); // Use createPackage
	}

	@GetMapping("/travelGet")
	public List<TravelPackage> travelGet() {
		return travelPackageService.getAllPackages(); // Use getAllPackages
	}

	@PostMapping("/packageCreation")
	public void packageCreation(@Valid @RequestBody TravelPackage travelPackage) {
		System.out.println("working");
		travelPackageService.createPackage(travelPackage);
	}

	@GetMapping("/packageDisplay")
	public List<TravelPackage> packageDisplay(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "minDuration", required = false) Integer minDuration,
			@RequestParam(value = "maxDuration", required = false) Integer maxDuration,
			@RequestParam(value = "services", required = false) String services) { // Changed to single service

		System.out.println(maxDuration);
		return travelPackageService.searchAndFilterPackages(title, minDuration, maxDuration, services);
	}

	@GetMapping("/package/{packageId}")
	public ResponseEntity<TravelPackage> getPackageById(@PathVariable int packageId) {
		Optional<TravelPackage> packageOptional = travelPackageService.getPackageById(packageId);
		if (packageOptional.isPresent()) {
			return ResponseEntity.ok(packageOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/reviews/{packageId}")
	public ResponseEntity<List<Review>> getReviewsByPackageId(@PathVariable int packageId) {
		System.out.println("Fetching reviews for package ID: " + packageId);
		List<Review> reviews = reviewService.getReviewsByPackageId(packageId);
		System.out.println("Reviews found: " + reviews.size());
		return ResponseEntity.ok(reviews);
	}
}
