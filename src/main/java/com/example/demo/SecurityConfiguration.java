package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.MyUserDetailsService;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// 1. all endpoints public
		/*
		 http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> {
			auth.anyRequest().permitAll();
		})
		.httpBasic(Customizer.withDefaults());
		return http.build();	
		 */

		// 2. some urls private, remaining are public
		/*
		 http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET,"/users").authenticated();			
			auth.requestMatchers(HttpMethod.DELETE,"/users/**").authenticated();
			auth.requestMatchers(HttpMethod.GET,"/users/**").authenticated();
			auth.anyRequest().permitAll();
		})
		.httpBasic(Customizer.withDefaults());

		return http.build();
		 */	

		// 3. role based access
		http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN");			
			auth.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET,"/users/**").hasRole("USER");
			auth.requestMatchers(HttpMethod.PUT,"/users/**").hasRole("USER");
			auth.requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.POST, "/carts").hasRole("USER");
			auth.requestMatchers(HttpMethod.GET, "/carts").hasRole("USER");
			auth.requestMatchers(HttpMethod.PUT, "/carts/**").hasRole("USER");
			auth.requestMatchers(HttpMethod.DELETE, "/carts/**").hasRole("USER");
			
			auth.anyRequest().permitAll();
			
		});
//		.httpBasic(Customizer.withDefaults());
	
		http.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	// 3. role based access - configure(), inMemoryUsers(), passwordEncoder()
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*
	@Bean
	public UserDetailsService inMemoryUsers() {
		UserDetails user1 = User.builder()
				.username("amit")
				.password(passwordEncoder().encode("amit@1234"))
				.roles("USER")
				.build();
		UserDetails user2 = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin@1234"))
				.roles("ADMIN")
				.build();
		UserDetails user3 = User.builder()
				.username("sam")
				.password(passwordEncoder().encode("sam@1234"))
				.roles("USER","ADMIN")
				.build();		
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}
*/
	
	// db login
//	@Autowired
//	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) 
			throws Exception {
		System.out.println("auth manager");
		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}
}
