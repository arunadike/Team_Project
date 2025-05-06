package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Users;
import com.Project3.Project3.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public List<Users> returnData() {
		try {
			return (List<Users>) userRepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching users: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void saveData(Users user) {
		try {
			userRepository.save(user);
			logger.info("User saved successfully");
		} catch (Exception e) {
			logger.error("Error saving user: {}", e.getMessage(), e);
			throw e;
		}
	}

	public Users getUserById(long userId) {
		try {
			Optional<Users> userOptional = userRepository.findById(userId);
			return userOptional.orElse(null); // Returns the User if found, otherwise null
		} catch (Exception e) {
			logger.error("Error fetching user by ID {}: {}", userId, e.getMessage(), e);
			throw e;
		}
	}

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public Users register(Users user) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			return user;
		} catch (Exception e) {
			logger.error("Error registering user: {}", e.getMessage(), e);
			throw e;
		}
	}

	public Users getUserByUsername(String username) {
		try {
			Users user = (userRepository.findByUsername(username));
			return user; // Or throw an exception if user not found: .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
		} catch (Exception e) {
			logger.error("Error fetching user by username {}: {}", username, e.getMessage(), e);
			throw e;
		}
	}
}
