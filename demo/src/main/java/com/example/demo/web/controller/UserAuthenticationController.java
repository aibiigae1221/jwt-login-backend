package com.example.demo.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.entity.User;
import com.example.demo.service.UserAuthenticationService;
import com.example.demo.web.authentication.JwtProcessor;
import com.example.demo.web.domain.LoginParameters;
import com.example.demo.web.domain.SignUpParameters;

@RestController
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService userAuthService;
	
	
	@Autowired
	private JwtProcessor jwtProcessor;
	
	@GetMapping("/user/signup")
	public void signUp(@Valid @RequestBody SignUpParameters params) {
		userAuthService.signUpUser(params);
	}

	@PostMapping("/user/login")
	public String login(@Valid @RequestBody LoginParameters params, HttpServletResponse response) {
		
		try{
			User user = userAuthService.loadUser(params);
			return jwtProcessor.generateJwt(user);
		}catch(BadCredentialsException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return "login failed";
		}
	}
	
}
