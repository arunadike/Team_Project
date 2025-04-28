package com.Project3.Project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project3.Project3.model.TravelPackage;

@Repository
public interface TravelPackageRepository extends CrudRepository<TravelPackage, Integer> {

    List<TravelPackage> findByTitleContainingIgnoreCase(String searchTerm);

    List<TravelPackage> findByDurationBetween(Integer minDuration, Integer maxDuration);

    List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetween(String searchTerm, Integer minDuration, Integer maxDuration);

    @Query("SELECT p FROM TravelPackage p WHERE p.duration BETWEEN :minDuration AND :maxDuration")
    List<TravelPackage> filterByDuration(@Param("minDuration") Integer minDuration, @Param("maxDuration") Integer maxDuration);

    // Corrected methods for filtering by includedService (singular to match entity)
    @Query("SELECT p FROM TravelPackage p WHERE " +
            "LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND " +
            "p.duration BETWEEN :minDuration AND :maxDuration AND " +
            "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
    List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServices(
            @Param("title") String title,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("service") String service,
            @Param("delimiter") String delimiter);

    @Query("SELECT p FROM TravelPackage p WHERE " +
            "LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND " +
            "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
    List<TravelPackage> findByTitleContainingIgnoreCaseAndIncludedServices(
            @Param("title") String title,
            @Param("service") String service,
            @Param("delimiter") String delimiter);

    @Query("SELECT p FROM TravelPackage p WHERE p.duration BETWEEN :minDuration AND :maxDuration AND (:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
    List<TravelPackage> findByDurationBetweenAndIncludedServices(
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("service") String service,
            @Param("delimiter") String delimiter);

    @Query("SELECT p FROM TravelPackage p WHERE (:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
    List<TravelPackage> filterByServices(@Param("service") String service, @Param("delimiter") String delimiter);
}