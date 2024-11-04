package com.ecommerce.app.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	
	public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${spring.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${spring.app.jwtSecret}")
	private String jwtSecretKey;
	
	// to get token from request.
	public String getJwtFromHeader (HttpServletRequest request) { // we pass it in headers Authorization:Bearer token
		String token = request.getHeader("Authorization");
		if(token!=null && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		
		return null;
	}
	
	// to create token using username.
	public String getJwtTokenFromUserName (UserDetails userDetails) {
		
		String userName = userDetails.getUsername();
		return Jwts.builder()
				.subject(userName)
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+jwtExpirationMs))
				.signWith(key())
				.compact();
		
	}
	
	//to create key for get jwt token form username method.
	public Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
	}
	
	//to get user name from the jwt token
	public String getUsernameFromJwtToken(String Token) {
		return Jwts.parser()
				.verifyWith((SecretKey)key())
				.build()
				.parseSignedClaims(Token)
				.getPayload().getSubject();
	}
	
	//validating Jwt tokens
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(authToken);
			return true;
		}catch(MalformedJwtException e) {
			logger.error("Invalid JWT token: {}",e.getMessage());
		}catch(ExpiredJwtException e) {
			logger.error("Jwt Token is Expired: {}",e.getMessage());
		}catch(UnsupportedJwtException e) {
			logger.error("Jwt Token is not supported: {}",e.getMessage());
		}catch(IllegalArgumentException e) {
			logger.error("JWT Claims String is Empty: {}",e.getMessage());
		}
		return false;
		
	}
}
