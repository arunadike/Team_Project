package com.Project3.Project3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Review; // Import the Review model
import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.service.ReviewService; // Import ReviewService
import com.Project3.Project3.service.TravelPackageService;

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
	public ResponseEntity<TravelPackage> packageCreation(
	    @Valid @RequestBody TravelPackage travelPackage) {

	    try {
	        TravelPackage createdPackage = travelPackageService.createPackage(travelPackage);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdPackage);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	}

	
	@DeleteMapping("delete/{packageId}")
    public ResponseEntity<String> deletePackage(@PathVariable int packageId) {
        try {
            travelPackageService.deletePackage(packageId);
            return new ResponseEntity<>("Package deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete package: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	@GetMapping("/packageDisplay")
	public List<TravelPackage> packageDisplay(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "minDuration", required = false) Integer minDuration,
			@RequestParam(value = "maxDuration", required = false) Integer maxDuration,
			@RequestParam(value = "services", required = false) String service, // Changed to single service
			@RequestParam(value = "minPrice", required = false) Double minPrice,
			@RequestParam(value = "maxPrice", required = false) Double maxPrice) {

		System.out.println("title: " + title + ", minDuration: " + minDuration + ", maxDuration: " + maxDuration + ", service: " + service + ", minPrice: " + minPrice + ", maxPrice: " + maxPrice);

		// 2. Filter by Title
		if (title != null && minDuration == null && maxDuration == null && service == null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by title: " + title);
			return travelPackageService.searchPackagesByTitle(title);
		}
		// 3. Filter by Duration
		else if (title == null && minDuration != null && maxDuration != null && service == null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by duration: " + minDuration + " - " + maxDuration);
			return travelPackageService.filterPackagesByDuration(minDuration, maxDuration);
		}
		// 4. Filter by Service
		else if (title == null && minDuration == null && maxDuration == null && service != null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by service: " + service);
			return travelPackageService.filterPackagesByService(service);
		}
		// 5. Filter by Price
		else if (title == null && minDuration == null && maxDuration == null && service == null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by price: " + minPrice + " - " + maxPrice);
			return travelPackageService.filterPackagesByPrice(minPrice, maxPrice);
		}
		// 6. Filter by Title and Duration
		else if (title != null && minDuration != null && maxDuration != null && service == null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by title and duration: " + title + ", " + minDuration + " - " + maxDuration);
			return travelPackageService.findPackagesByTitleAndDuration(title, minDuration, maxDuration);
		}
		// 7. Filter by Title and Service
		else if (title != null && minDuration == null && maxDuration == null && service != null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by title and service: " + title + ", " + service);
			return travelPackageService.findPackagesByTitleAndService(title, service);
		}
		// 8. Filter by Title and Price
		else if (title != null && minDuration == null && maxDuration == null && service == null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by title and price: " + title + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByTitleAndPrice(title, minPrice, maxPrice);
		}
		// 9. Filter by Duration and Service
		else if (title == null && minDuration != null && maxDuration != null && service != null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by duration and service: " + minDuration + " - " + maxDuration + ", " + service);
			return travelPackageService.findPackagesByDurationAndService(minDuration, maxDuration, service);
		}
		// 10. Filter by Duration and Price
		else if (title == null && minDuration != null && maxDuration != null && service == null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by duration and price: " + minDuration + " - " + maxDuration + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByDurationAndPrice(minDuration, maxDuration, minPrice, maxPrice);
		}
		// 11. Filter by Service and Price
		else if (title == null && minDuration == null && maxDuration == null && service != null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by service and price: " + service + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByServiceAndPrice(service, minPrice, maxPrice);
		}
		// 12. Filter by Title, Duration, and Service
		else if (title != null && minDuration != null && maxDuration != null && service != null && minPrice == null && maxPrice == null) {
			System.out.println("Filter by title, duration, and service: " + title + ", " + minDuration + " - " + maxDuration + ", " + service);
			return travelPackageService.findPackagesByTitleAndDurationAndService(title, minDuration, maxDuration, service);
		}
		// 13. Filter by Title, Duration, and Price
		else if (title != null && minDuration != null && maxDuration != null && service == null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by title, duration, and price: " + title + ", " + minDuration + " - " + maxDuration + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByTitleAndDurationAndPrice(title, minDuration, maxDuration, minPrice, maxPrice);
		}
		// 14. Filter by Title, Service, and Price
		else if (title != null && minDuration == null && maxDuration == null && service != null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by title, service, and price: " + title + ", " + service + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByTitleAndServiceAndPrice(title, service, minPrice, maxPrice);
		}
		// 15. Filter by Duration, Service, and Price
		else if (title == null && minDuration != null && maxDuration != null && service != null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by duration, service, and price: " + minDuration + " - " + maxDuration + ", " + service + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByDurationAndServiceAndPrice(minDuration, maxDuration, service, minPrice, maxPrice);
		}
		// 16. Filter by Title, Duration, Service, and Price
		else if (title != null && minDuration != null && maxDuration != null && service != null && minPrice != null && maxPrice != null) {
			System.out.println("Filter by title, duration, service, and price: " + title + ", " + minDuration + " - " + maxDuration + ", " + service + ", " + minPrice + " - " + maxPrice);
			return travelPackageService.findPackagesByTitleAndDurationAndServiceAndPrice(title, minDuration, maxDuration, service, minPrice, maxPrice);
		}

		else {
			System.out.println("No filters applied");
			return travelPackageService.packageDisplay(); // Default return, if no filters match.
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


}
