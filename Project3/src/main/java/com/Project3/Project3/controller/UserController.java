package com.Project3.Project3.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.Users;
import com.Project3.Project3.service.JwtService;
import com.Project3.Project3.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
//	@Autowired
//	UserService userService;
//	
//	@GetMapping("/userGet")
//	@ResponseBody
//	public List<User> get()
//	{
//		return  userService.returnData();
//	}
//	@PostMapping("/userPost")
//	public void  post(@RequestBody User user)
//	{
//		userService.saveData(user);
//		//return "hi";
//	}
//	@PostMapping("/users")
//	@ResponseBody
//	public String display()
//	{
//		return "hi";
//	}
//	@PostMapping("/user")
//	@ResponseBody
//	public void post(@RequestBody User user)
//	{
//		userService.saveData(user);
//	}
//	@GetMapping("/agent")
//	public String arun()
//	{
//		return "agent";
//	}
//	
//	@GetMapping("/index")
//	public String index()
//	{
//		return "index";
//	}
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService service;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
		try {
			System.out.println("Entered");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("Exit");
			String roles = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(", "));

			// Fetch user details from the database.  Assuming you have a UserService.
			com.Project3.Project3.model.Users user = service.getUserByUsername(username); //  <--  ADDED THIS LINE

			System.out.println(username + " " + password + " - Login successful, Roles: " + roles + ", Session ID: " + request.getSession().getId());

			Map<String, Object> responseBody = new HashMap<>(); // Changed to Map<String, Object>
			responseBody.put("token", jwtService.generateToken(username));
			responseBody.put("message", "Login successful!");
			responseBody.put("username", username); // Add username
			responseBody.put("roles", roles);    // Add roles
			responseBody.put("userId", user.getUserId()); // Add user ID (assuming your User class has an getId() method)
//			responseBody.put("email", user.getEmail());
			// Add other relevant user data here (e.g., email, ID, etc.)
			request.getSession(true); // Ensure a session is created after successful authentication

			System.out.println(username + " " + user.getUserId() + " - Login successful, Session ID: " + request.getSession().getId() + " JWT=" + jwtService.generateToken(username));
			System.out.println(responseBody);
			return ResponseEntity.ok(responseBody);
			//        return jwtService.generateToken(username);
		} catch (AuthenticationException e) {

			System.err.println("Login failed for user: " + username + " - " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login failed: " + e.getMessage()));
			//        return "error";
		}
	}

	// Removed the custom /login POST endpoint

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		System.out.print(user);
		service.register(user);
		return user;
	}

	@GetMapping("/greet")
	public String greetAdmin() {
		return "Hello, Administrator!";
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			return ResponseEntity.ok("Logout successful!");
		} else {
			return ResponseEntity.ok("No user logged in to logout.");
		}
	}

	@GetMapping("/data")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> getSecuredData() {
		// This endpoint should only be accessible to authenticated users.
		//  You might want to add additional authorization checks here.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName(); // Get the username of the logged-in user.
			System.out.println(username);
			return ResponseEntity.ok("This is secured data for secured users");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
//		return "Secured Data";
	}
	
	

}
