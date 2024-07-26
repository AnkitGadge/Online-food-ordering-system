package com.hashedin.foodapp.foodservice.exceptions;

public class NoFoodFoundByIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1268829232222966171L;
	
	public NoFoodFoundByIdException(String message) {
		super(message);
	}

}
