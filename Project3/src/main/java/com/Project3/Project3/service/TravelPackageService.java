package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

	private static final Logger logger = LoggerFactory.getLogger(TravelPackageService.class);

	@Autowired
	TravelPackageRepository travelPackageRepository;

	private static final String SERVICE_DELIMITER = ", "; // Define this constant

	@Transactional
	public void saveData(TravelPackage travelPackage) {
		try {
			travelPackageRepository.save(travelPackage);
			logger.info("TravelPackage saved successfully");
		} catch (Exception e) {
			logger.error("Error saving TravelPackage: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Transactional
	public List<TravelPackage> returnData() {
		try {
			return (List<TravelPackage>) travelPackageRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching TravelPackages: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Transactional
	public TravelPackage createPackage(TravelPackage travelPackage) {
		try {
			return travelPackageRepository.save(travelPackage);
		} catch (Exception e) {
			logger.error("Error creating TravelPackage: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Transactional
	public List<TravelPackage> packageDisplay() {
		try {
			return (List<TravelPackage>) travelPackageRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching TravelPackages: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Transactional
	public Optional<TravelPackage> getPackageById(int packageId) {
		try {
			return travelPackageRepository.findById(packageId);
		} catch (Exception e) {
			logger.error("Error fetching TravelPackage by ID {}: {}", packageId, e.getMessage(), e);
			throw e;
		}
	}

	// 2. Filter by Title
	@Transactional
	public List<TravelPackage> searchPackagesByTitle(String title) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCase(title);
		} catch (Exception e) {
			logger.error("Error searching TravelPackages by title {}: {}", title, e.getMessage(), e);
			throw e;
		}
	}

	// 3. Filter by Duration
	@Transactional
	public List<TravelPackage> filterPackagesByDuration(Integer minDuration, Integer maxDuration) {
		try {
			return travelPackageRepository.findByDurationBetween(minDuration, maxDuration);
		} catch (Exception e) {
			logger.error("Error filtering TravelPackages by duration between {} and {}: {}", minDuration, maxDuration, e.getMessage(), e);
			throw e;
		}
	}

	// 4. Filter by Included Service
	@Transactional
	public List<TravelPackage> filterPackagesByService(String service) {
		try {
			return travelPackageRepository.filterByServices(service.trim(), SERVICE_DELIMITER);
		} catch (Exception e) {
			logger.error("Error filtering TravelPackages by service {}: {}", service, e.getMessage(), e);
			throw e;
		}
	}

	// 5. Filter by Price
	@Transactional
	public List<TravelPackage> filterPackagesByPrice(double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByPriceBetween(minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error filtering TravelPackages by price between {} and {}: {}", minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 6. Filter by Title and Duration
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndDuration(String title, Integer minDuration, Integer maxDuration) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {} and duration between {} and {}: {}", title, minDuration, maxDuration, e.getMessage(), e);
			throw e;
		}
	}

	// 7. Filter by Title and Included Service
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndService(String title, String service) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndIncludedServices(title, service.trim(), SERVICE_DELIMITER);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {} and service {}: {}", title, service, e.getMessage(), e);
			throw e;
		}
	}

	// 8. Filter by Title and Price
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndPrice(String title, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndPriceBetween(title, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {} and price between {} and {}: {}", title, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 9. Filter by Duration and Included Service
	@Transactional
	public List<TravelPackage> findPackagesByDurationAndService(Integer minDuration, Integer maxDuration, String service) {
		try {
			return travelPackageRepository.findByDurationBetweenAndIncludedServices(minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by duration between {} and {} and service {}: {}", minDuration, maxDuration, service, e.getMessage(), e);
			throw e;
		}
	}

	// 10. Filter by Duration and Price
	@Transactional
	public List<TravelPackage> findPackagesByDurationAndPrice(Integer minDuration, Integer maxDuration, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByDurationBetweenAndPriceBetween(minDuration, maxDuration, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by duration between {} and {} and price between {} and {}: {}", minDuration, maxDuration, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 11. Filter by Included Service and Price
	@Transactional
	public List<TravelPackage> findPackagesByServiceAndPrice(String service, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByIncludedServicesAndPriceBetween(service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by service {} and price between {} and {}: {}", service, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 12. Filter by Title, Duration, and Included Service
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndDurationAndService(String title, Integer minDuration, Integer maxDuration, String service) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServices(
					title, minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {}, duration between {} and {}, and service {}: {}", title, minDuration, maxDuration, service, e.getMessage(), e);
			throw e;
		}
	}

	// 13. Filter by Title, Duration, and Price
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndDurationAndPrice(String title, Integer minDuration, Integer maxDuration, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndPriceBetween(title, minDuration, maxDuration, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {}, duration between {} and {}, and price between {} and {}: {}", title, minDuration, maxDuration, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 14. Filter by Title, Included Service, and Price
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndServiceAndPrice(String title, String service, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndIncludedServicesAndPriceBetween(title, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {}, service {}, and price between {} and {}: {}", title, service, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 15. Filter by Duration, Included Service, and Price
	@Transactional
	public List<TravelPackage> findPackagesByDurationAndServiceAndPrice(Integer minDuration, Integer maxDuration, String service, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByDurationBetweenAndIncludedServicesAndPriceBetween(minDuration, maxDuration, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by duration between {} and {}, service {}, and price between {} and {}: {}", minDuration, maxDuration, service, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	// 16. Filter by Title, Duration, Included Service, and Price
	@Transactional
	public List<TravelPackage> findPackagesByTitleAndDurationAndServiceAndPrice(String title, Integer minDuration, Integer maxDuration, String service, double minPrice, double maxPrice) {
		try {
			return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServicesAndPriceBetween(
					title, minDuration, maxDuration, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
		} catch (Exception e) {
			logger.error("Error finding TravelPackages by title {}, duration between {} and {}, service {}, and price between {} and {}: {}", title, minDuration, maxDuration, service, minPrice, maxPrice, e.getMessage(), e);
			throw e;
		}
	}

	public List<TravelPackage> filterByMultipleServices(List<TravelPackage> packages, List<String> services) {
		List<TravelPackage> filteredPackages = packages;
		for (String service : services) {
			filteredPackages = filterByIncludedService(filteredPackages, service);
		}
		return filteredPackages;
	}

	private List<TravelPackage> filterByIncludedService(List<TravelPackage> packages, String service) {
		return packages.stream()
				.filter(p -> (SERVICE_DELIMITER + p.getIncludedService() + SERVICE_DELIMITER)
						.toLowerCase()
						.contains((SERVICE_DELIMITER + service.trim() + SERVICE_DELIMITER).toLowerCase()))
				.collect(java.util.stream.Collectors.toList());
	}
	
	public void deletePackage(int packageId) {
        Optional<TravelPackage> packageToDelete = travelPackageRepository.findById(packageId); // Assuming your entity is in com.example.travelapp.model
        if (packageToDelete.isPresent()) {
            travelPackageRepository.delete(packageToDelete.get()); // Use delete() method of JPA repository
        } else {
            throw new RuntimeException("Package not found with id: " + packageId);
        }
    }
	 public List<TravelPackage> getPackagesByUserId(int userId) {
	        return travelPackageRepository.findByUser_Userid(userId);
	    }

} 
