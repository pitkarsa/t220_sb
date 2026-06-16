package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		// fetch the User object from db using username provided at login time
		//i.e. email , as we login using email
		
		User dbUser = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Please provide valid email id"));
				
		// return the UserDetails object 
		UserDetails userDetails = new User(
				dbUser.getEmail(),
				dbUser.getPassword(),
				dbUser.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role))
					.toList(),
				dbUser.getId(),
				dbUser.getRoles(),
				dbUser.getUsername()
				);
		return userDetails;
	}
}
