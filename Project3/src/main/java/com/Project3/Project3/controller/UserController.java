package com.Project3.Project3.controller;
 
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.Project3.Project3.model.Users;
import com.Project3.Project3.service.JwtService;
import com.Project3.Project3.service.UserService;
 
import jakarta.servlet.http.HttpServletRequest;
 
@RestController
public class UserController {
 
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
	@Autowired
	private AuthenticationManager authenticationManager;
 
	@Autowired
	private JwtService jwtService;
 
	@Autowired
	private UserService service;
 
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
		logger.info("Login attempt for user: {}", username);
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
 
			String roles = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(", "));
 
			// Fetch user details from the database.  Assuming you have a UserService.
			com.Project3.Project3.model.Users user = service.getUserByUsername(username); //  <--  ADDED THIS LINE
 
			Map<String, Object> responseBody = new HashMap<>(); // Changed to Map<String, Object>
			responseBody.put("token", jwtService.generateToken(username));
			responseBody.put("message", "Login successful!");
			responseBody.put("username", username); // Add username
			responseBody.put("roles", roles);    // Add roles
			responseBody.put("userId", user.getUserId()); // Add user ID (assuming your User class has an getId() method)
			request.getSession(true); // Ensure a session is created after successful authentication
 
			logger.info("User {} logged in successfully. Roles: {}, Session ID: {}, JWT: {}", username, roles, request.getSession().getId(), jwtService.generateToken(username));
			logger.debug("Response body: {}", responseBody);
			return ResponseEntity.ok(responseBody);
		} catch (AuthenticationException e) {
			logger.error("Login failed for user: {} - {}", username, e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login failed: " + e.getMessage()));
		}
	}
 
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		logger.info("Registration attempt for user: {}", user.getUsername());
		try {
			service.register(user);
			logger.info("User registered successfully: {}", user.getUsername());
			return user;
		} catch (Exception e) {
			logger.error("Error registering user: {} - {}", user.getUsername(), e.getMessage());
			throw e; // Re-throw the exception so that Spring's default exception handling can manage it.
		}
	}
}