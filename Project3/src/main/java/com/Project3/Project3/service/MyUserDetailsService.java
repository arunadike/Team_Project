package com.Project3.Project3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.Users;
import com.Project3.Project3.model.UserPrincipal;
import com.Project3.Project3.repository.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=repo.findByUsername(username);
		System.out.println(user);
		if(user==null) {
			System.out.println("bad Credentials");
			throw new UsernameNotFoundException("Bad Credentials");
		}
		return new UserPrincipal(user);
	}

}
