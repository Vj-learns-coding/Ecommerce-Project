package com.ecommerce.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecommerce.app.model.User;
import com.ecommerce.app.repository.UserRepository;

@Component
public class AuthUtils {

	@Autowired
	UserRepository userRepository;
	
	public String loggedInEmail() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName())
				    .orElseThrow(()-> new UsernameNotFoundException("User was not found"));
		return user.getEmail();
		
		
	}
	
	public Long loggedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName())
				    .orElseThrow(()-> new UsernameNotFoundException("User was not found"));
		return user.getUserId();
		
	}
	
	public User loggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName())
				    .orElseThrow(()-> new UsernameNotFoundException("User was not found"));
		return user;
	}
}
