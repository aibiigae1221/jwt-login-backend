package com.example.demo.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LoginParameters implements Serializable{

	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^[A-Za-z0-9_]+@[A-Za-z0-9_]+\\.[A-Za-z_]{2,}$", message="이메일 형식이 유효하지 않습니다.")
	@NotNull(message="이메일은 필수 입력값입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@NotNull(message = "비밀번호는 필수 입력값입니다.")
	private String password;
	
	public LoginParameters() {}

	public LoginParameters(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
