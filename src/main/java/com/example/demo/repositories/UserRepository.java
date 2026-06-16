package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@RestResource(exported = false)
	public User save(User user);//POST and PUT
	
	// checking if the email already exists in User table
	public boolean existsByEmail(String email);
	public Optional<User> findByEmail(String email);
	
	// checking if the mobile already exists in User table
	public boolean existsByMobile(String mobile);
}
