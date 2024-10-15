package com.ecommerce.app.payload;

public class APIResponse {
	
	private String message;
	

	public APIResponse(String message) {
		super();
		this.message = message;
	}


	public APIResponse() {
		// TODO Auto-generated constructor stub
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
