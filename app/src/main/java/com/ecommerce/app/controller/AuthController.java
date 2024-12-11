package com.ecommerce.app.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.model.AppRole;
import com.ecommerce.app.model.Role;
import com.ecommerce.app.model.User;
import com.ecommerce.app.repository.RoleRepository;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.security.jwt.JwtUtils;
import com.ecommerce.app.security.request.LoginRequest;
import com.ecommerce.app.security.request.SignUpRequest;
import com.ecommerce.app.security.response.LoginResponse;
import com.ecommerce.app.security.response.MessageResponse;
import com.ecommerce.app.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		} catch (AuthenticationException e) {
			Map<String,Object> map = new HashMap<>();
			map.put("message", "Bad Credentials");
			map.put("Status", false);
			return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
			
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		ResponseCookie jwtTokenCookie = jwtUtils.generateJwtCookie(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
		
		LoginResponse loginResponse = new LoginResponse(userDetails.getId(),userDetails.getUsername(),roles);
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtTokenCookie.toString()).body(loginResponse);
		
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> signoutUser(){
		
		ResponseCookie jwtTokenCookie = jwtUtils.emptyCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtTokenCookie.toString())
								  .body(new MessageResponse("you have been logged out!"));

	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest signUpRequest) {
		
		if(userRepository.existsByUsername(signUpRequest.getUsername())){
			return  ResponseEntity.badRequest().body("User With the username "+signUpRequest.getUsername()+" already exists");
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())){
			return  ResponseEntity.badRequest().body("User With the email "+signUpRequest.getEmail()+" already exists");
		}
		
		User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));
		List<String> userRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if( userRoles==null || userRoles.size()==0 ) {
			Role roleUser = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseThrow(
					()-> new RuntimeException("The role has not been found"));
			roles.add(roleUser);
		}
		else {
			for(String role:userRoles) {
				
				switch(role.toLowerCase()) {
					case "admin":
						Role roleAdmin = roleRepository.findByRoleName(AppRole.ROLE_ADMIN).orElseThrow(
														()-> new RuntimeException("The role has not been found"));
						roles.add(roleAdmin);
					case "seller":
						Role roleSeller = roleRepository.findByRoleName(AppRole.ROLE_SELLER).orElseThrow(
								()-> new RuntimeException("The role has not been found"));
						roles.add(roleSeller);
					case "user" :
						Role roleUser = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseThrow(
								()-> new RuntimeException("The role has not been found"));
						roles.add(roleUser);
					default:
						throw new RuntimeException("you have entered a role which is not existing in this application");
						
				}
			}
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity(new MessageResponse("user has been successfully registered"),HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(Authentication authentication){
		if(authentication ==null) {
			return null;
		}
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		
		List<String> roles = userDetails.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
		
		LoginResponse loginResponse = new LoginResponse(userDetails.getId(),userDetails.getUsername(),roles);
		
		return  ResponseEntity.ok().body(loginResponse);
	}
}
