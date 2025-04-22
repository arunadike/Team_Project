package com.Project3.Project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.User;
import com.Project3.Project3.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> returnData() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	public void saveData(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		System.out.println("Done");
	}
	
	

}
