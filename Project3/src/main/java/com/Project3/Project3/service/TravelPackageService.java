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

    @Transactional
    public List<TravelPackage> searchPackagesByTitle(String title) {
        return travelPackageRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public List<TravelPackage> filterPackagesByDuration(Integer minDuration, Integer maxDuration) {
        return travelPackageRepository.filterByDuration(minDuration, maxDuration);
    }

    @Transactional
    public List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetween(String title, Integer minDuration, Integer maxDuration) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetween(title, minDuration, maxDuration);
    }

    private static final String SERVICE_DELIMITER = ", ";

    public List<TravelPackage> findPackagesByTitleAndDurationAndService(String title, Integer minDuration, Integer maxDuration, String service) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServices(
                title, minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
    }

    public List<TravelPackage> findPackagesByTitleAndService(String title, String service) {
        return travelPackageRepository.findByTitleContainingIgnoreCaseAndIncludedServices(
                title, service.trim(), SERVICE_DELIMITER);
    }

    public List<TravelPackage> findPackagesByDurationAndService(Integer minDuration, Integer maxDuration, String service) {
        return travelPackageRepository.findByDurationBetweenAndIncludedServices(
                minDuration, maxDuration, service.trim(), SERVICE_DELIMITER);
    }

    public List<TravelPackage> findPackagesByService(String service) {
        return travelPackageRepository.filterByServices(service.trim(), SERVICE_DELIMITER);
    }
    
    public List<TravelPackage> filterByMultipleServices(List<TravelPackage> packages, List<String> services) {
        List<TravelPackage> filteredPackages = packages;
        for (String service : services) {
            filteredPackages = filterByIncludedService(filteredPackages, service);
        }
        return filteredPackages;
    }
    public List<TravelPackage> filterByIncludedService(List<TravelPackage> packages, String service) {
        return packages.stream()
                .filter(p -> (TravelPackageService.SERVICE_DELIMITER + p.getIncludedService() + TravelPackageService.SERVICE_DELIMITER)
                        .toLowerCase()
                        .contains((TravelPackageService.SERVICE_DELIMITER + service.trim() + TravelPackageService.SERVICE_DELIMITER).toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

}
