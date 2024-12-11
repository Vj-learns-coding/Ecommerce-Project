package com.ecommerce.app.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryJointPointJwt implements AuthenticationEntryPoint { // custom handling for unauthorized requests
	

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		final Map<String, Object> body = new HashMap<>();
		body.put("status",HttpServletResponse.SC_UNAUTHORIZED);
		body.put("error", "UnAuthorized");
		body.put("meassage", authException.getMessage());
		body.put("path", request.getServletPath());
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(),body);
		
	}

}
