package com.hashedin.foodapp.orderservice.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ItemsAlreadyPresentException.class)
	public ResponseEntity<ExceptionalMessage> itemsAlreadyPresentException(ItemsAlreadyPresentException itemsAlreadyPresentException){
		String errMsg = itemsAlreadyPresentException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.NOT_FOUND);
	}

}
