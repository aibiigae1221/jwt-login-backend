package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.UserRepository;
import com.example.demo.data.entity.User;
import com.example.demo.web.domain.SignUpParameters;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User user;

	@Override
	public long getCountWholeUsers() {
		return userRepository.count();
	}

	@Override
	public User signUpUser(SignUpParameters params) {
		populateParamsIntoEntity(params);
		return userRepository.save(user);
	}

	private void populateParamsIntoEntity(SignUpParameters params) {
		user.setEmail(params.getEmail());
		user.setPassword(params.getPassword());
		user.setNickname(params.getNickname());
	}

}
