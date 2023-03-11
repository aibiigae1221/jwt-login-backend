package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.web.authentication.JwtProcessor;
import com.example.demo.web.authentication.JwtProcessorImpl;
import com.example.demo.web.controller.ResponseMap;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UtilityContainer {

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		return new ObjectMapper();
	}
	
	@Scope("prototype")
	@Bean
	public ResponseMap responseMap(){
		return new ResponseMap();
	}
	
	@Bean
	public JwtProcessor jwtProcessor() {
		return new JwtProcessorImpl();
	}
}
