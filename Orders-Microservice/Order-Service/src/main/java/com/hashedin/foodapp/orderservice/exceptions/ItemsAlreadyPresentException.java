package com.hashedin.foodapp.orderservice.exceptions;

public class ItemsAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466064005530400275L;
	
	public ItemsAlreadyPresentException(String message) {
		super(message);
	}

}
