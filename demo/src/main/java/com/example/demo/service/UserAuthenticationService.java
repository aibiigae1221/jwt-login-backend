package com.example.demo.service;

import com.example.demo.data.entity.User;
import com.example.demo.web.domain.SignUpParameters;

public interface UserAuthenticationService {

	long getCountWholeUsers();

	User signUpUser(SignUpParameters params);

}
