package com.Project3.Project3.repository;
 
import org.springframework.stereotype.Repository;
 
import com.Project3.Project3.model.Contactus;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
 
@Repository
 
	public interface ContactRepository extends JpaRepository<Contactus, Long> {
	}
 