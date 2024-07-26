package com.hashedin.foodapp.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5640366250922650611L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
