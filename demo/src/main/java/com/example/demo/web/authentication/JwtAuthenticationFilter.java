package com.example.demo.web.authentication;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtProcessor jwtProcessor;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		logger.info("filter is working on..");
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null) {
			logger.info("found an authorization header in my filter..");	
			String jwt = authHeader.substring("bearer: ".length());
			Map<String, Object> claims = jwtProcessor.validateJwt(jwt);
			
			UsernamePasswordAuthenticationToken token = createToken(jwt, claims);
			
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		filterChain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken createToken(String jwt, Map<String, Object> claims) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(jwt, claims);
		token.setAuthenticated(false);
		return token;
	}

}
