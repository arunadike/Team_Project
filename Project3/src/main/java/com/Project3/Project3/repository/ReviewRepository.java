package com.Project3.Project3.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Project3.Project3.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    // Corrected query to access TravelPackage ID.  Use JPQL.
    @Query("SELECT r FROM Review r WHERE r.package1.packageId = :packageId")
    List<Review> findByPackage1_PackageId(int packageId);

}
