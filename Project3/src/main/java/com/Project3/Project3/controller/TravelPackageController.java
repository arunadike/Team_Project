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
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/packageCreation")
	public void packageCreation(@Valid @RequestBody TravelPackage travelPackage) {
		System.out.println("working");
		travelPackageService.createPackage(travelPackage);
	}

//	@GetMapping("/packageDisplay")
//	public List<TravelPackage> packageDisplay(
//			@RequestParam(value = "title", required = false) String title,
//			@RequestParam(value = "minDuration", required = false) Integer minDuration,
//			@RequestParam(value = "maxDuration", required = false) Integer maxDuration) {
//		if (title != null && !title.isEmpty() && minDuration != null && maxDuration != null) {
//			return travelPackageService.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
//		} else if (title != null && !title.isEmpty()) {
//			return travelPackageService.searchPackagesByTitle(title);
//		} else if (minDuration != null && maxDuration != null) {
//			return travelPackageService.filterPackagesByDuration(minDuration, maxDuration);
//		} else {
//			return travelPackageService.packageDisplay();
//		}
//	}
	
	
	@GetMapping("/packageDisplay")
	public List<TravelPackage> packageDisplay(
	        @RequestParam(value = "title", required = false) String title,
	        @RequestParam(value = "minDuration", required = false) Integer minDuration,
	        @RequestParam(value = "maxDuration", required = false) Integer maxDuration,
	        @RequestParam(value = "services", required = false) List<String> services) {

	    if (title != null && !title.isEmpty() && minDuration != null && maxDuration != null && services != null && !services.isEmpty()) {
	        List<TravelPackage> results = travelPackageService.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
	        return travelPackageService.filterByMultipleServices(results, services);
	    } else if (title != null && !title.isEmpty() && minDuration != null && maxDuration != null) {
	        return travelPackageService.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
	    } else if (title != null && !title.isEmpty() && services != null && !services.isEmpty()) {
	        List<TravelPackage> results = travelPackageService.searchPackagesByTitle(title);
	        return travelPackageService.filterByMultipleServices(results, services);
	    } else if (minDuration != null && maxDuration != null && services != null && !services.isEmpty()) {
	        List<TravelPackage> results = travelPackageService.filterPackagesByDuration(minDuration, maxDuration);
	        return travelPackageService.filterByMultipleServices(results, services);
	    } else if (title != null && !title.isEmpty()) {
	        return travelPackageService.searchPackagesByTitle(title);
	    } else if (minDuration != null && maxDuration != null) {
	        return travelPackageService.filterPackagesByDuration(minDuration, maxDuration);
	    } else if (services != null && !services.isEmpty()) {
	        List<TravelPackage> results = travelPackageService.packageDisplay();
	        return travelPackageService.filterByMultipleServices(results, services);
	    } else {
	        return travelPackageService.packageDisplay();
	    }
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
	
	
//	@GetMapping("/search")
//    public ResponseEntity<List<TravelPackage>> searchPackages(@RequestParam("title") String title) {
//        List<TravelPackage> results = travelPackageService.searchPackagesByTitle(title);
//        return ResponseEntity.ok(results);
//    }
//	
//	@GetMapping("/filterByDuration")
//    public ResponseEntity<List<TravelPackage>> filterPackagesByDuration(
//            @RequestParam("minDuration") Integer minDuration,
//            @RequestParam("maxDuration") Integer maxDuration) {
//        List<TravelPackage> results = travelPackageService.filterPackagesByDuration(minDuration, maxDuration);
//        return ResponseEntity.ok(results);
//    }
}

