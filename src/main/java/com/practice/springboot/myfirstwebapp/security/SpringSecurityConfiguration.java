package com.practice.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults; 

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or DB- whenever we want to store username or passsword
	//InMemory
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)	//UserDetails- interface
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("udemy", "dummy");
		UserDetails userDetails2 = createNewUser("om", "dummydummy");

		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input) ;
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder).		
									username(username).password(password).roles("USER","ADMIN").build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	
		//Whatever password we're providing, spring security is encoding using this algo
		//When configuring user details, we need to use that specific algo
		return new BCryptPasswordEncoder();
	}
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disabled
	//Frames
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());	//ensure all requests are authenticated
		http.formLogin(withDefaults());		//If unauthorized request, we confirm formLogin; a page is shown where we can collect userid and pwd
		
		http.csrf().disable();				//disabling csrf
		http.headers().frameOptions().disable();		//enabling use of frames in our app
		return http.build();
	}
	
}