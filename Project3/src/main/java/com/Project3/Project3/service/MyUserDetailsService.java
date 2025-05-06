package com.Project3.Project3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Users;
import com.Project3.Project3.model.UserPrincipal;
import com.Project3.Project3.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users user = repo.findByUsername(username);
			logger.info("User found: {}", user);
			if (user == null) {
				logger.warn("Bad Credentials for username: {}", username);
				throw new UsernameNotFoundException("Bad Credentials");
			}
			return new UserPrincipal(user);
		} catch (Exception e) {
			logger.error("Error loading user by username {}: {}", username, e.getMessage(), e);
			throw e;
		}
	}

}
