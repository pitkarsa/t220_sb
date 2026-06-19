package com.example.demo.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@NotNull(message="username is required")
	private String username;
	
	@Column(unique = true) // db constraint
	//@NotNull(message="email is required")
	//@Email(message="please enter the valid email")
	private String email;
	
	//@NotNull(message = "Password is required")
	//@Pattern(regexp = "[a-zA-Z0-9@#_]*", 
//	message="Password can contain alphabets, digits, @#_ only")
	//@Size(min=6, max=12, message="Password must contain 6 to 12 charachters")
	private String password;
	
	@Column(unique = true) // db constraint
	//@NotNull(message="mobile is required")
	//@Size(min=10, max=10, message="Mobile must contain 10 digits")
	private String mobile;
	
	@ElementCollection // create separate table "user_roles" with FK 
	private List<String> roles;	
	
	@OneToMany(mappedBy = "user")
	private List<Cart> myCart;
	
	@OneToMany(mappedBy = "user")
	private List<Orders> myOrders;
	
	public List<Orders> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(List<Orders> myOrders) {
		this.myOrders = myOrders;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public List<Cart> getMyCart() {
		return myCart;
	}
	public void setMyCart(List<Cart> myCart) {
		this.myCart = myCart;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	// Methods and variables for dbLogin
	@Transient // wont create db column
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	/* optional parent interface methods 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	*/
	
	// Constructors
	
	public User() {}
	public User(
			// spring security needs 3 param  for authentication and authorization
			String email, String password, Collection<? extends GrantedAuthority> authorities,
			// variables required at the frontend(React) 
			int id, List<String> roles, String username) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", mobile="
				+ mobile + ", roles=" + roles + ", myCart=" + myCart + ", authorities=" + authorities + "]";
	}
	
	
	
	
}
