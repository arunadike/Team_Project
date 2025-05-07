package com.Project3.Project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project3.Project3.model.TravelPackage;

@Repository
public interface TravelPackageRepository extends CrudRepository<TravelPackage, Integer> {

	// 1. No filter - findAll() is already provided by CrudRepository

	// 2. Filter by Title
	List<TravelPackage> findByTitleContainingIgnoreCase(String title);

	List<TravelPackage> findByUser_Userid(int userId);

	// 3. Filter by Duration
	List<TravelPackage> findByDurationBetween(Integer minDuration, Integer maxDuration);

	// 4. Filter by Included Service
	@Query("SELECT p FROM TravelPackage p WHERE LOWER(CONCAT(:delimiter, p.includedService, :delimiter)) LIKE LOWER(CONCAT('%', :delimiter, :services, :delimiter, '%'))")
	List<TravelPackage> filterByServices(@Param("services") String service, @Param("delimiter") String delimiter);

	// 5. Filter by Price
	List<TravelPackage> findByPriceBetween(double minPrice, double maxPrice);

	// 6. Filter by Title and Duration
	List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetween(String title, Integer minDuration,
			Integer maxDuration);

	// 7. Filter by Title and Included Service
	@Query("SELECT p FROM TravelPackage p WHERE LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND (:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
	List<TravelPackage> findByTitleContainingIgnoreCaseAndIncludedServices(@Param("title") String title,
			@Param("service") String service, @Param("delimiter") String delimiter);

	// 8. Filter by Title and Price
	List<TravelPackage> findByTitleContainingIgnoreCaseAndPriceBetween(String title, double minPrice, double maxPrice);

	// 9. Filter by Duration and Included Service
	@Query("SELECT p FROM TravelPackage p WHERE p.duration BETWEEN :minDuration AND :maxDuration AND (:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
	List<TravelPackage> findByDurationBetweenAndIncludedServices(@Param("minDuration") Integer minDuration,
			@Param("maxDuration") Integer maxDuration, @Param("service") String service,
			@Param("delimiter") String delimiter);

	// 10. Filter by Duration and Price
	List<TravelPackage> findByDurationBetweenAndPriceBetween(Integer minDuration, Integer maxDuration, double minPrice,
			double maxPrice);

	// 11. Filter by Included Service and Price
	@Query("SELECT p FROM TravelPackage p WHERE (:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%')) AND p.price BETWEEN :minPrice AND :maxPrice")
	List<TravelPackage> findByIncludedServicesAndPriceBetween(@Param("service") String service,
			@Param("delimiter") String delimiter, @Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	// 12. Filter by Title, Duration, and Included Service
	@Query("SELECT p FROM TravelPackage p WHERE " + "LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND "
			+ "p.duration BETWEEN :minDuration AND :maxDuration AND "
			+ "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%'))")
	List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServices(
			@Param("title") String title, @Param("minDuration") Integer minDuration,
			@Param("maxDuration") Integer maxDuration, @Param("service") String service,
			@Param("delimiter") String delimiter);

	// 13. Filter by Title, Duration, and Price
	List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetweenAndPriceBetween(String title,
			Integer minDuration, Integer maxDuration, double minPrice, double maxPrice);

	// 14. Filter by Title, Included Service, and Price
	@Query("SELECT p FROM TravelPackage p WHERE " + "LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND "
			+ "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%')) AND "
			+ "p.price BETWEEN :minPrice AND :maxPrice")
	List<TravelPackage> findByTitleContainingIgnoreCaseAndIncludedServicesAndPriceBetween(@Param("title") String title,
			@Param("service") String service, @Param("delimiter") String delimiter, @Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	// 15. Filter by Duration, Included Service, and Price
	@Query("SELECT p FROM TravelPackage p WHERE " + "p.duration BETWEEN :minDuration AND :maxDuration AND "
			+ "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%')) AND "
			+ "p.price BETWEEN :minPrice AND :maxPrice")
	List<TravelPackage> findByDurationBetweenAndIncludedServicesAndPriceBetween(
			@Param("minDuration") Integer minDuration, @Param("maxDuration") Integer maxDuration,
			@Param("service") String service, @Param("delimiter") String delimiter, @Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);

	// 16. Filter by Title, Duration, Included Service, and Price
	@Query("SELECT p FROM TravelPackage p WHERE " + "LOWER(p.title) LIKE LOWER(concat('%', :title, '%')) AND "
			+ "p.duration BETWEEN :minDuration AND :maxDuration AND "
			+ "(:delimiter || p.includedService || :delimiter) LIKE LOWER(concat('%', :delimiter, :service, :delimiter, '%')) AND "
			+ "p.price BETWEEN :minPrice AND :maxPrice")
	List<TravelPackage> findByTitleContainingIgnoreCaseAndDurationBetweenAndIncludedServicesAndPriceBetween(
			@Param("title") String title, @Param("minDuration") Integer minDuration,
			@Param("maxDuration") Integer maxDuration, @Param("service") String service,
			@Param("delimiter") String delimiter, @Param("minPrice") double minPrice,
			@Param("maxPrice") double maxPrice);
}
