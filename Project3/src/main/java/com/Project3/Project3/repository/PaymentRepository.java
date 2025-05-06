package com.Project3.Project3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Project3.Project3.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
