package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.service.UserAuthenticationService;
import com.example.demo.web.domain.SignUpParameters;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest
class DemoApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class); 
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private SignUpParameters signUpParams;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	
	private static final String CUSTOMER_EMAIL = "mytestid123@gmail.com";
	private static final String CUSTOMER_PASSWORD = "123123";
	private static final String CUSTOMER_NICKNAME = "김경훈";
	
	@BeforeEach
	public void initFixtures() {
		signUpParams.setEmail(CUSTOMER_EMAIL);
		signUpParams.setPassword(CUSTOMER_PASSWORD);
		signUpParams.setNickname(CUSTOMER_NICKNAME);
	}
	
	@Test
	public void signUpPage() throws Exception {
		
		// 정상적인 경우
		accessSignUpPage(signUpParams, status().isOk());

		long count = userAuthenticationService.getCountWholeUsers();
		assertEquals(1, count);
		
		// 회원정보 입력값이 비정상적일 경우
		signUpParams.setEmail("");
		accessSignUpPage(signUpParams, status().isBadRequest());
		
		signUpParams.setEmail(CUSTOMER_EMAIL);
		signUpParams.setPassword("");
		accessSignUpPage(signUpParams, status().isBadRequest());
	}

	private void accessSignUpPage(SignUpParameters inputParams, ResultMatcher resultMatcher) throws Exception {
		String jsonString = objectMapper.writeValueAsString(inputParams);
		logger.debug(jsonString);
		
		mvc.perform(get("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andDo(print())
			;//.andExpect(resultMatcher);
	}
}
