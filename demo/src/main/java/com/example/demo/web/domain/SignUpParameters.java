package com.example.demo.web.domain;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Scope("prototype")
@Component
public class SignUpParameters implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp = "^[A-Za-z0-9_]+@[A-Za-z0-9_]+\\.[A-Za-z_]{2,3}$", message="이메일 형식이 유효하지 않습니다.")
	@NotNull
	private String email;

	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@NotNull(message = "비밀번호는 필수 입력값입니다.")
	private String password;
	
	@NotBlank(message = "성함은 필수 입력값입니다.")
	@NotNull(message = "성함은 필수 입력값입니다.")
	private String nickname;
	
	public SignUpParameters() {}
	
	
	public SignUpParameters(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
