package com.ecommerce.app.security.response;

import java.util.List;

public class LoginResponse {
	
	private long id;
	private String JwtToken;
	private String Username;
	private List<String> roles;
	
	
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse( long id,String username,String jwtToken, List<String> roles) {
		super();
		this.id=id;
		JwtToken = jwtToken;
		Username = username;
		this.roles = roles;
	}
	public LoginResponse(long id2, String username2, List<String> roles2) {
		this.id=id2;
		Username = username2;
		this.roles = roles2;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
