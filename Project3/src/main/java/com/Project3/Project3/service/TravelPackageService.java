package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

    @Autowired
    TravelPackageRepository travelPackageRepository;

    private static final String SERVICE_DELIMITER = ", "; // Define this constant

    @Transactional
    public void saveData(TravelPackage travelPackage) {
        travelPackageRepository.save(travelPackage);
    }

    @Transactional
    public List<TravelPackage> returnData() {
        return (List<TravelPackage>) travelPackageRepository.findAll();
    }

    @Transactional
    public void createPackage(TravelPackage travelPackage) {
        travelPackageRepository.save(travelPackage);
    }

    @Transactional
    public List<TravelPackage> packageDisplay() {
        return (List<TravelPackage>) travelPackageRepository.findAll();
    }

    @Transactional
    public Optional<TravelPackage> getPackageById(int packageId) {
        return travelPackageRepository.findById(packageId);
    }

    // 2. Filter by Title
    @Transactional
    public List<TravelPackage> searchPackagesByTitle(String title) {
        return travelPackageRepository.findByTitleContainingIgnoreCase(title);
    }

    // 3. Filter by Duration
    @Transactional
    public List<TravelPackage> filterPackagesByDuration(Integer minDuration, Integer maxDuration) {
        return travelPackageRepository.findByDurationBetween(minDuration, maxDuration);
    }

    // 4. Filter by Included Service
    @Transactional
    public List<TravelPackage> filterPackagesByService(String service) {
        return travelPackageRepository.filterByServices(service.trim(), SERVICE_DELIMITER);
    }

    // 5. Filter by Price
    @Transactional
    public List<TravelPackage> filterPackagesByPrice(double minPrice, double maxPrice) {
        return travelPackageRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // 6. Filter by Title and Duration
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndDuration(String title, Integer minDuration, Integer maxDuration) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
    }

    // 7. Filter by Title and Included Service
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndService(String title, String service) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndIncludedServices(title, service.trim(), SERVICE_DELIMITER);
    }

    // 8. Filter by Title and Price
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndPrice(String title, double minPrice, double maxPrice) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndPriceBetween(title, minPrice, maxPrice);
    }

    // 9. Filter by Duration and Included Service
    @Transactional
    public List<TravelPackage> findPackagesByDurationAndService(Integer minDuration, Integer maxDuration, String service) {
        return travelPackageRepository.findByDurationBetweenAndIncludedServices(minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
    }

    // 10. Filter by Duration and Price
    @Transactional
    public List<TravelPackage> findPackagesByDurationAndPrice(Integer minDuration, Integer maxDuration, double minPrice, double maxPrice) {
        return travelPackageRepository.findByDurationBetweenAndPriceBetween(minDuration, maxDuration, minPrice, maxPrice);
    }

    // 11. Filter by Included Service and Price
    @Transactional
    public List<TravelPackage> findPackagesByServiceAndPrice(String service, double minPrice, double maxPrice) {
        return travelPackageRepository.findByIncludedServicesAndPriceBetween(service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
    }

    // 12. Filter by Title, Duration, and Included Service
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndDurationAndService(String title, Integer minDuration, Integer maxDuration, String service) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServices(
                title, minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
    }

    // 13. Filter by Title, Duration, and Price
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndDurationAndPrice(String title, Integer minDuration, Integer maxDuration, double minPrice, double maxPrice) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndPriceBetween(title, minDuration, maxDuration, minPrice, maxPrice);
    }

    // 14. Filter by Title, Included Service, and Price
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndServiceAndPrice(String title, String service, double minPrice, double maxPrice) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndIncludedServicesAndPriceBetween(title, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
    }

    // 15. Filter by Duration, Included Service, and Price
    @Transactional
    public List<TravelPackage> findPackagesByDurationAndServiceAndPrice(Integer minDuration, Integer maxDuration, String service, double minPrice, double maxPrice) {
        return travelPackageRepository.findByDurationBetweenAndIncludedServicesAndPriceBetween(minDuration, maxDuration, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
    }

    // 16. Filter by Title, Duration, Included Service, and Price
    @Transactional
    public List<TravelPackage> findPackagesByTitleAndDurationAndServiceAndPrice(String title, Integer minDuration, Integer maxDuration, String service, double minPrice, double maxPrice) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServicesAndPriceBetween(
                title, minDuration, maxDuration, service.trim(), SERVICE_DELIMITER, minPrice, maxPrice);
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
}
