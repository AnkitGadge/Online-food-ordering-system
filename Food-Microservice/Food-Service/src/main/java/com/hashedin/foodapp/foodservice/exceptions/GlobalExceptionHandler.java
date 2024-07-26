package com.hashedin.foodapp.foodservice.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoRestaurantFoundException.class)
	public ResponseEntity<ExceptionalMessage> noRestaurantFoundException(NoRestaurantFoundException noRestaurantFoundException){
		String errMsg = noRestaurantFoundException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoFoodFoundByIdException.class)
	public ResponseEntity<ExceptionalMessage> noFoodFoundByIdException(NoFoodFoundByIdException noFoodFoundByIdException){
		String errMsg = noFoodFoundByIdException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoCategoryFoundException.class)
	public ResponseEntity<ExceptionalMessage> noCategoryFoundException(NoCategoryFoundException noCategoryFoundException){
		String errMsg = noCategoryFoundException.getMessage();
		return new ResponseEntity<ExceptionalMessage>(new ExceptionalMessage(errMsg, LocalDateTime.now(), false),
				HttpStatus.NOT_FOUND);
	}

}
