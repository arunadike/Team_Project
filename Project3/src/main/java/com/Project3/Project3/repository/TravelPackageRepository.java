package com.Project3.Project3.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Project3.Project3.model.TravelPackage;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {

    // 1. Combined Search and Filter
    @Query("SELECT p FROM TravelPackage p WHERE " +
            "(:title IS NULL OR LOWER(p.title) LIKE LOWER(concat('%', :title, '%'))) AND " +
            "(:minDuration IS NULL OR p.duration >= :minDuration) AND " +
            "(:maxDuration IS NULL OR p.duration <= :maxDuration) AND " +
            "(:service IS NULL OR LOWER(p.includedService) LIKE LOWER(concat('%', :service, '%')))")
    List<TravelPackage> searchAndFilter(
            @Param("title") String title,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("service") String service);

}
