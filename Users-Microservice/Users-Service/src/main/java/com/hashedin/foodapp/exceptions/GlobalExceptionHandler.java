package com.hashedin.foodapp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionalMessage> userNotFoundException(UserNotFoundException userNotFoundException){
		String errMsg = userNotFoundException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<ExceptionalMessage> userAlreadyPresentException(UserAlreadyPresentException userAlreadyPresentException ){
		String errMsg = userAlreadyPresentException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.BAD_REQUEST);
	}

}
