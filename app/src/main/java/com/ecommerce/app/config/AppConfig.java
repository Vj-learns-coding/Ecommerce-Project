package com.ecommerce.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper mapperBean() {
		return new ModelMapper();
	}

}
