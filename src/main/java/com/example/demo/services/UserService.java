package com.example.demo.services;

import com.example.demo.controllers.UserController;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.models.UserDto;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {	

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;	
	
	public User addUser(UserDto dto) {
		//check the uniqueness of email and mobile  
		boolean existingEmail = repository.existsByEmail(dto.getEmail());
		if(existingEmail ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exists");
		}
		boolean existingMobile = repository.existsByMobile(dto.getMobile());
		if(existingMobile ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mobile already exists");
		}	
		System.out.println("mobile and email is unique");
		// User registration
		User newUser = new User();
		newUser.setUsername(dto.getUsername());
		newUser.setEmail(dto.getEmail());
		newUser.setMobile(dto.getMobile());
		
		// set the encoded password
		newUser.setPassword(encoder.encode(dto.getPassword()));
		
		// adding the roles
		if(dto.getRoles()== null) {
			newUser.setRoles(Arrays.asList("ROLE_USER"));
		}
		else 
			newUser.setRoles(dto.getRoles());
		
		User dbUser = repository.save(newUser);
		return dbUser;			
		//
	}	
}
