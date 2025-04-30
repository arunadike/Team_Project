package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.User;
import com.Project3.Project3.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> returnData() {
		return (List<User>) userRepository.findAll();
	}

	public void saveData(User user) {
		userRepository.save(user);
		System.out.println("User saved successfully");
	}

	public User getUserById(long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.orElse(null); // Returns the User if found, otherwise null
	}
}