package com.Project3.Project3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Project3.Project3.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

}
