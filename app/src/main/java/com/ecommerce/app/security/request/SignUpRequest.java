package com.ecommerce.app.security.request;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
	
	@NotBlank
	@Size(min=4,max=30)
	private String username;
	@NotBlank
	@Size(min=4,max=40)
	private String password;
	
	private List<String> roles;
	
	@NotBlank
	@Email
	@Size(max=50)
	private String email;

	
	
	

	public SignUpRequest(@NotBlank @Size(min = 4, max = 30) String username,
			@NotBlank @Size(min = 4, max = 40) String password, List<String> roles,
			@NotBlank @Email @Size(max = 50) String email) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
