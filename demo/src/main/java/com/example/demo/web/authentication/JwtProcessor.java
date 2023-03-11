package com.example.demo.web.authentication;

import java.util.Map;

import com.example.demo.data.entity.User;

public interface JwtProcessor {

	String generateJwt(User user);

	Map<String, Object> validateJwt(String jwt);

}
