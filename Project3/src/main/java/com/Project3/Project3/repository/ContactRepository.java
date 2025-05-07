package com.Project3.Project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project3.Project3.model.Contactus;

@Repository
public interface ContactRepository extends JpaRepository<Contactus, Long> {

}
