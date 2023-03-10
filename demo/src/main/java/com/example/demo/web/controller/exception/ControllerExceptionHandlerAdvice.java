package com.example.demo.web.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.web.controller.ResponseMap;

@ControllerAdvice
public class ControllerExceptionHandlerAdvice {

	// private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlerAdvice.class);
	
	@Autowired
	private ResponseMap responseMap;	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		responseMap.getMap().put("error", errors);
		
		return new ResponseEntity<>(responseMap.getMap(), null, HttpStatus.BAD_REQUEST);
	}
}
