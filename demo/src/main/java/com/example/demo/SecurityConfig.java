package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.web.authentication.JwtAuthenticationFilter;
import com.example.demo.web.authentication.JwtAuthenticationProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests( 
					authorize -> authorize
									.antMatchers("/user/signup", "/user/login").permitAll()
									.anyRequest().authenticated())
			
			.authenticationProvider(jwtAuthenticationProvider())
			
			
			.csrf()
				.disable()
				
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				
			.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			
			;
			
		
		return http.build();
	}
	
	@Bean
	public OncePerRequestFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public AuthenticationProvider jwtAuthenticationProvider() {
		return new JwtAuthenticationProvider();
	}
	
	
	
}
