package com.example.demo.service;

import javax.validation.Valid;

import com.example.demo.data.entity.User;
import com.example.demo.web.domain.LoginParameters;
import com.example.demo.web.domain.SignUpParameters;

public interface UserAuthenticationService {

	long getCountWholeUsers();

	User signUpUser(SignUpParameters params);

	User loadUser(@Valid LoginParameters params);

	User loadUser(String email, String password);

}
