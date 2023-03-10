package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserAuthenticationService;
import com.example.demo.web.domain.SignUpParameters;

import jakarta.validation.Valid;

@RestController
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService userAuthService;
	
	@GetMapping("/user/signup")
	public void signUp(@Valid @RequestBody SignUpParameters params) {
		
		userAuthService.signUpUser(params);
	}

}
