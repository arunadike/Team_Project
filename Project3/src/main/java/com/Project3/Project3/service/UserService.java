package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Users;
import com.Project3.Project3.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<Users> returnData() {
		return (List<Users>) userRepository.findAll();
	}

	public void saveData(Users user) {
		userRepository.save(user);
		System.out.println("User saved successfully");
	}

	public Users getUserById(long userId) {
		Optional<Users> userOptional = userRepository.findById(userId);
		return userOptional.orElse(null); // Returns the User if found, otherwise null
	}
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	public Users getUserByUsername(String username) {
		Users user =(userRepository.findByUsername(username));
		return user; // Or throw an exception if user not found: .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
	}
}