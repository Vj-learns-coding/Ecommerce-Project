package com.ecommerce.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.app.security.jwt.AuthEntryJointPointJwt;
import com.ecommerce.app.security.jwt.AuthTokenFilter;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	

    @Autowired
    private AuthEntryJointPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
											.requestMatchers("/v3/api-docs/**").permitAll()
											.requestMatchers("/h2-console/**").permitAll()
										  //.requestMatchers("/api/admin/**").permitAll()
										  //.requestMatchers("/api/public/**").permitAll()
											.requestMatchers("/swagger-ui/**").permitAll()
										  .requestMatchers("/api/test/**").permitAll()
											.requestMatchers("/images/**").permitAll()
								         .anyRequest().authenticated());
		http.authenticationProvider(daoAuthenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers(headers->headers.frameOptions(frameOptions->frameOptions.sameOrigin()));
		return http.build();
	}
	
	//The primary purpose of WebSecurityCustomizer is to define which requests should bypass all security filters completely.
	   @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web -> web.ignoring().requestMatchers("/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**"));
	    }
	
	
}
