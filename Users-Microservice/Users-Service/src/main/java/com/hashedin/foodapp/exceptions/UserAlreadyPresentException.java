package com.hashedin.foodapp.exceptions;

public class UserAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720950729069119941L;
	
	public UserAlreadyPresentException(String message) {
		super(message);
	}

}
