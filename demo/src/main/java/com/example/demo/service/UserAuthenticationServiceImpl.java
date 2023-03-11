package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.UserRepository;
import com.example.demo.data.entity.User;
import com.example.demo.web.domain.LoginParameters;
import com.example.demo.web.domain.SignUpParameters;

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

	@Override
	public User loadUser(LoginParameters params) {
		return loadUser(params.getEmail(), params.getPassword());
	}

	@Override
	public User loadUser(String email, String password) {
		return userRepository.findOneByEmailAndPassword(email, password)
				.orElseThrow(() -> new BadCredentialsException("bad credential exception at email(" + email + ") password(" + password + ")"));
	}

}
