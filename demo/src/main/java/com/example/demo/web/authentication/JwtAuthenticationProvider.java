package com.example.demo.web.authentication;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.demo.service.UserAuthenticationService;

public class JwtAuthenticationProvider implements AuthenticationProvider{

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	
	@Override
	public Authentication	 authenticate(Authentication authentication) throws AuthenticationException {
		
		logger.info("provider is working on..");
		
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		@SuppressWarnings("unchecked")
		Map<String,Object> claims = (Map<String, Object>)token.getCredentials();
		
		String email = claims.get("email").toString();
		String password = claims.get("password").toString();
		
		userAuthenticationService.loadUser(email, password);
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
