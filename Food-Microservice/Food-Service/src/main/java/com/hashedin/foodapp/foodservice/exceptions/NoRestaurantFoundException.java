package com.hashedin.foodapp.foodservice.exceptions;

public class NoRestaurantFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8510827803974654132L;
	
	public NoRestaurantFoundException(String message) {
		super(message);
	}

}
