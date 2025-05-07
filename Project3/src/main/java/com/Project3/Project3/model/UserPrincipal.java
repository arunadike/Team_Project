package com.Project3.Project3.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	// Removed @Autowired
	private Users user;

	public UserPrincipal(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Assuming your Users entity has a 'getRole()' method that returns a String
		String userRole = user.getRole();
		if (userRole == null || userRole.isEmpty()) {
			return Collections.emptyList(); // Or handle the case where no role is assigned
		}
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userRole)); // Added ROLE_ prefix
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Or implement logic from your User entity
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Or implement logic from your User entity
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Or implement logic from your User entity
	}

	@Override
	public boolean isEnabled() {
		return true; // Or implement logic from your User entity
	}
}