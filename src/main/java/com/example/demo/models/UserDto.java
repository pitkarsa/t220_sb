package com.example.demo.models;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// data validation
public class UserDto {	
	@NotNull(message="username is required")
	private String username;
	
	@NotNull(message="email is required")
	@Email(message="please enter the valid email")
	private String email;
	
	@NotNull(message = "Password is required")
	@Pattern(regexp = "[a-zA-Z0-9@#_]*", message="Password can contain alphabets, digits, @#_ only")
	@Size(min=6, max=12, message="Password must contain 6 to 12 charachters")
	private String password;
	
	@NotNull(message="mobile is required")
	@Size(min=10, max=10, message="Mobile must contain 10 digits")
	private String mobile;
	
	private List<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	

	
	
}
