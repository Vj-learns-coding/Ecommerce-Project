package com.ecommerce.app.security.jwt;

import java.util.List;

public class LoginResponse {
	
	private String JwtToken;
	private String Username;
	private List<String> roles;
	
	
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse( String username,String jwtToken, List<String> roles) {
		super();
		JwtToken = jwtToken;
		Username = username;
		this.roles = roles;
	}
	public String getJwtToken() {
		return JwtToken;
	}
	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
