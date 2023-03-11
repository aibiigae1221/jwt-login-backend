package com.example.demo.web.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.example.demo.data.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtProcessorImpl implements JwtProcessor{

	@Value("${jwt.duration-in-second}")
	private Long durationInSecond;
	
	@Value("${jwt.Secret}")
	private String jwtSecret;
	
	@Override
	public String generateJwt(User user) {
		
		Map<String, Object> claimMap = new HashMap<String, Object>();
		claimMap.put("email", user.getEmail());
		claimMap.put("password", user.getPassword());
		Long durationComputed = durationInSecond * 1000L;
		
		Date expirationDate = new Date(new Date().getTime() + durationComputed);
		
		return Jwts
				.builder()
					.setClaims(claimMap)
					.setExpiration(expirationDate)
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	@Override
	public Map<String, Object> validateJwt(String jwt) {
		return Jwts.
				parser()
					.setSigningKey(jwtSecret)
					.parseClaimsJws(jwt)
					.getBody();
	}

}
